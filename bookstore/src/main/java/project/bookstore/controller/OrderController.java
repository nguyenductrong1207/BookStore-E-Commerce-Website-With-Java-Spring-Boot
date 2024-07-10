package project.bookstore.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.bookstore.entity.*;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.entity.user.User;
import project.bookstore.exception.AuthorNotFoundException;
import project.bookstore.exception.OrderNotFoundException;
import project.bookstore.service.CategoryService;
import project.bookstore.service.OrderService;
import project.bookstore.service.UserService;

import java.io.IOException;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    CategoryService categoryService;

    // Order Controller In Admin
    @GetMapping("/admin-orders")
    public String getAllBook(Model model) {
        List<Order> listOrders = orderService.getAllOrders();
        model.addAttribute("listOrders", listOrders);
        model.addAttribute("pageTitle", "Order List");
        return "Admin/admin-orders";
    }

    @GetMapping("/admin-add-order")
    public String showNewForm(Model model) {
        model.addAttribute("order", new Order());

        return "Admin/admin-add-order";
    }

    @PostMapping("/admin-add-order/save")
    public String saveOrder(Order order, RedirectAttributes ra) {
        orderService.save(order);
        ra.addFlashAttribute("message", "The order has been saved successfully");
        return "redirect:/admin-orders";
    }

    @GetMapping("/admin-orders/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Order order = orderService.get(id);
            model.addAttribute("order", order);
            model.addAttribute("pageTitle", "Edit Order (ID: " + id + ")");

            return "Admin/admin-add-order";
        } catch (OrderNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin-orders";
        }
    }

    @GetMapping("/admin-orders/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            orderService.delete(id);
            ra.addFlashAttribute("message", "The order ID " + id + " has been deleted");
        } catch (OrderNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin-orders";
    }

    @GetMapping("/admin-orders/detail/{id}")
    public String viewOrderDetails(@PathVariable("id") Integer id, Model model,
                                   RedirectAttributes ra) {
        try {
            Order order = orderService.get(id);
            model.addAttribute("order", order);

            return "Admin/order_details_modal";

        } catch (OrderNotFoundException ex) {
            ra.addFlashAttribute("message", ex.getMessage());
            return "redirect:/admin-orders";
        }
    }

    // Order Controller In Client
    @GetMapping("/order-history")
    public String clientViewOrderDetails(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        User user = getAuthenticatedUser(userDetails);
        List<Order> ordersList = orderService.getOrdersByUser(user);

        model.addAttribute("ordersList", ordersList);

        return "Client/order-history";
    }

    @GetMapping("/order-detail/{orderId}")
    public String viewOrderDetails(@PathVariable("orderId") Integer orderId, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) throws OrderNotFoundException {
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        User user = getAuthenticatedUser(userDetails);
        Order order = orderService.get(orderId);

        if (!order.getUser().equals(user)) {
            throw new OrderNotFoundException("Order not found or you do not have permission to view it.");
        }

        model.addAttribute("order", order);

        return "Client/order-detail";
    }

    private User getAuthenticatedUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        String email = userDetails.getUsername();
        return userService.getUserByEmail(email);
    }

}
