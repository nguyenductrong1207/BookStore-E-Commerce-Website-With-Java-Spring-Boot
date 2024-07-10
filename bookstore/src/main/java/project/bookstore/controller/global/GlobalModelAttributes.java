package project.bookstore.controller.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import project.bookstore.entity.Book;
import project.bookstore.entity.Category;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.service.*;

import java.util.List;

@ControllerAdvice
public class GlobalModelAttributes {
    @Autowired private BookService bookService;
    @Autowired private CategoryService categoryService;
    @Autowired private CartService cartService;

    @ModelAttribute
    public void showInformation(Model model){
        // Notification if user log in or not
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication == null || authentication instanceof AnonymousAuthenticationToken)){
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            // Get Shopping Cart Total Number Of Items
            int numberOfCartItems = cartService.getTotalNumberOfItems(userDetails.getUser());
            model.addAttribute("numberOfCartItems", numberOfCartItems);
        }

        // Get All Categories Name from DB to Homepage
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        // Get All Books from DB to Homepage
        List<Book> listBooks = bookService.getAllBook();
        model.addAttribute("listBooks", listBooks);
    }
}
