package project.bookstore.controller;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.bookstore.entity.Author;
import project.bookstore.entity.Book;
import project.bookstore.entity.Category;
import project.bookstore.entity.Slider;
import project.bookstore.entity.Slider;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.entity.user.User;
import project.bookstore.exception.CategoryNotFoundException;
import project.bookstore.repository.CategoryRepository;
import project.bookstore.service.*;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private CartService cartService;
    @Autowired
    private SliderService sliderService;

    @ModelAttribute
    public void showInformation(Model model, Integer page, Integer size) {
        if (page == null || page < 0) {
            page = 0;
        }
        if (size == null || size <= 0) {
            size = 5;
        }
        // notification if user log in or not
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication == null || authentication instanceof AnonymousAuthenticationToken)) {

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            // Get Shopping Cart Total Number Of Items
            int numberOfCartItems = cartService.getTotalNumberOfItems(userDetails.getUser());
            model.addAttribute("numberOfCartItems", numberOfCartItems);
        }

        // Get All Categories Name from DB to Homepage
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        // Get All Books from DB to Homepage
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> newsPage = bookService.getPaginatedBooks(pageable);
        model.addAttribute("newsPage", newsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", newsPage.getTotalPages());

        // Get All Sliders from DB to Homepage
        List<Slider> sliders = sliderService.getSelectedSlider();
        model.addAttribute("sliders", sliders);
    }

    @GetMapping("/books-list")
    public String getBooksList(Model model, @RequestParam(defaultValue = "0") int page) {
        showInformation(model, page, 5);
        return "Client/books-list";
    }

    @GetMapping("/books-grid-view")
    public String getBooksGridView(Model model, @RequestParam(defaultValue = "0") int page) {
        showInformation(model, page, 8);
        return "Client/books-grid-view";
    }

    @GetMapping("/books-grid-view-sidebar")
    public String getBooksGridViewSidebar(Model model, @RequestParam(defaultValue = "0") int page) {
        showInformation(model, page, 6);
        return "Client/books-grid-view-sidebar";
    }

    @GetMapping("/books-list-view-sidebar")
    public String getBooksListViewSidebar(Model model, @RequestParam(defaultValue = "0") int page) {
        showInformation(model, page, 5);
        return "Client/books-list-view-sidebar";
    }

    @GetMapping("/books-list/{category}")
    public String getBookListByCategory(@PathVariable String category, Model model) {
        Set<Book> listBooks = categoryService.findByCategoryName(category).getBooks();
        model.addAttribute("listBooks", listBooks);

        return "Client/books-list";
    }

    @GetMapping("/books-grid-view/{category}")
    public String getBookViewListByCategory(@PathVariable String category, Model model) {
        Set<Book> listBooks = categoryService.findByCategoryName(category).getBooks();
        listBooks.stream().sorted();
        model.addAttribute("listBooks", listBooks);

        return "Client/books-list";
    }

    @GetMapping("/admin-books")
    public String getAllBook(Model model) {
        List<Book> listBooks = bookService.getAllBook();
        model.addAttribute("listBooks", listBooks);
        model.addAttribute("pageTitle", "Book List");
        return "Admin/admin-books";
    }

    @RequestMapping("/books-detail/{id}")
    public String getBookDetail(@PathVariable("id") Integer id, Model model) {
        Book book = bookService.getBookById(id);
        List<Book> listBooks = bookService.getAllBook();

        model.addAttribute("book", book);
        model.addAttribute("listBooks", listBooks);

        return "Client/books-detail";
    }

    @RequestMapping("/admin-books/edit/{id}")
    public String editBook(@PathVariable("id") Integer id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("pageTitle", "Edit Book (ID: " + id + ")");

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("authors", authorService.listAll());
        model.addAttribute("publishers", publisherService.getAllPublisher());
        return "Admin/admin-add-book";
    }

    @RequestMapping("/admin-books/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteById(id);
        return "redirect:/admin-books";
    }

    @GetMapping("/admin-add-book")
    public String showNewTitle(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("pageTitle", "Add New Book");

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("authors", authorService.listAll());
        model.addAttribute("publishers", publisherService.getAllPublisher());
        return "Admin/admin-add-book";
    }

    @PostMapping("/admin-add-book/save")
    public String saveBook(@ModelAttribute(name = "book") Book book,
            @RequestParam("bookImage") MultipartFile multipartFile,
            @RequestParam(name = "categories") Set<String> categories) throws IOException {
        try {
            Book savedBook = bookService.save(book);

            String uploadedImageUrl = cloudinaryService.uploadFile(multipartFile,
                    "Admin/books/" + savedBook.getBook_id());
            savedBook.setBook_image(uploadedImageUrl);

            Set<Category> selectedCategory = new HashSet<>();
            for (String category : categories) {
                selectedCategory.add(categoryService.get(Integer.parseInt(category)));
            }
            book.setCategories(selectedCategory);

            System.out.println(categories);
            bookService.save(savedBook);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (CategoryNotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/admin-books";
    }

}
