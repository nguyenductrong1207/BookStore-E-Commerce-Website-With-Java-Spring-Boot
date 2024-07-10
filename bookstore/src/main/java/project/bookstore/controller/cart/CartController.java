package project.bookstore.controller.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.bookstore.entity.Cart;
import project.bookstore.entity.Category;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.entity.user.User;
import project.bookstore.service.CartService;
import project.bookstore.service.CategoryService;
import project.bookstore.service.UserService;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @GetMapping("/shop-cart")
    public String viewCart(Model model, @AuthenticationPrincipal CustomUserDetails userDetails){
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        // View Cart
        User user = getAuthenticatedUser(userDetails);
        List<Cart> carts = cartService.listCart(user);

        float estimatedTotal = 0.0F;

        for (Cart cart : carts) {
            estimatedTotal += cart.getSubtotal();
        }

        model.addAttribute("carts", carts);
        model.addAttribute("estimatedTotal", estimatedTotal);

        return "Client/shop-cart";
    }

    private User getAuthenticatedUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        String email = userDetails.getUsername();
        return userService.getUserByEmail(email);
    }

}
