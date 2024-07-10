package project.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import project.bookstore.entity.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.entity.user.User;
import project.bookstore.exception.NewsNotFoundException;
import project.bookstore.service.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    BookService bookService;

    @Autowired
    CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private SliderService sliderService;
    @Autowired
    private NewsService newsService;

    // Get all needed information from DB and show to all pages
    
    @ModelAttribute
    public void showInformation(Model model){
        //notification if user log in or not
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

        // Get All Sliders from DB to Homepage
        List<Slider> sliders = sliderService.getSelectedSlider();
        model.addAttribute("sliders", sliders);
    }

    @GetMapping("/")
    public String home() {
        return "Client/index";
    }

    @GetMapping("/about-us")
    public String getAboutUs() {
        return "Client/about-us";
    }

    @GetMapping("/blog-detail/{id}")
    public String getBlogDetail(@PathVariable("id") Integer id, Model model) throws NewsNotFoundException {
        // Get All Catagories Name
        News news = newsService.get(id);
        if (news != null) {
            List<String> paragraphs = Arrays.asList(news.getDescription_news().split("\n"));
            model.addAttribute("news", news);
            model.addAttribute("paragraphs", paragraphs);
        }
        model.addAttribute("news", news);
        return "Client/blog-detail";
    }

    @GetMapping("/blog-list-sidebar")
    public String getBlogListSidebar(Model model,  @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size) {
        // Get All Catagories Name
//        List<News> listNews = newsService.listAll();
//        model.addAttribute("listNews", listNews);
        Pageable pageable = PageRequest.of(page, size);
        Page<News> newsPage = newsService.getPaginatedNews(pageable);
        model.addAttribute("newsPage", newsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", newsPage.getTotalPages());
        return "Client/blog-list-sidebar";
    }

    @GetMapping("/coming-soon")
    public String getComingSoon() {
        return "Client/coming-soon";
    }

    @GetMapping("/contact-us")
    public String getContactUs() {
        return "Client/contact-us";
    }

    @GetMapping("/404")
    public String get404() {
        return "/Client/error-404";
    }

    @GetMapping("/faq")
    public String getFAQ() {
        return "Client/faq";
    }

    @GetMapping("/pricing")
    public String getPricing() {
        return "Client/pricing";
    }

    @GetMapping("/privacy-policy")
    public String getPrivacyPolicy() {
        return "Client/privacy-policy";
    }

    @GetMapping("/services")
    public String getServices() {
        return "Client/services";
    }

    @GetMapping("/shop-login")
    public String getShopLogin() {
        return "Client/shop-login";
    }

    @GetMapping("/support")
    public String getSupport() {
        return "Client/support";
    }

    @GetMapping("/under-construction")
    public String getUnderConstruction() {
        return "Client/under-construction";
    }

}
