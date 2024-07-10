package project.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.bookstore.entity.Category;
import project.bookstore.entity.News;
import project.bookstore.exception.CategoryNotFoundException;
import project.bookstore.exception.InvalidNewsException;
import project.bookstore.exception.NewsNotFoundException;
import project.bookstore.service.CategoryService;
import project.bookstore.service.CloudinaryService;
import project.bookstore.service.NewsService;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class NewsController {
    @Autowired
    private NewsService service;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/admin-news")
    public String showNews(Model model) {
        List<News> listNews = service.listAll();
        model.addAttribute("listNews", listNews);
model.addAttribute("pageTitle","Article List");
        return "Admin/admin-news";
    }

    @GetMapping("/admin-add-news")
    public String showNewsForm(Model model) {
        model.addAttribute("news", new News());
        model.addAttribute("pageTitle", "Add New Article");
        model.addAttribute("categories", categoryService.getAllCategories());

        return "Admin/admin-add-news";
    }

    @PostMapping("/admin-add-news/save")
    public String saveNews(@ModelAttribute(name = "news") News news, RedirectAttributes ra,
                           @RequestParam("fileImage") MultipartFile multipartFile,
                           @RequestParam(value = "categories", required = false) List<Integer> categoryIds) {
        try {
            if (!categoryIds.isEmpty()) {
                Set<Category> selectedCategories = categoryIds.stream()
                        .map(id -> {
                            try {
                                return categoryService.get(id);
                            } catch (CategoryNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());
                news.setCategories(selectedCategories);
            } else {
                news.setCategories(new HashSet<>());
            }

            if (!multipartFile.isEmpty()) {
                String imageUrl = cloudinaryService.uploadFile(multipartFile, "Admin/news/" + news.getId());
                news.setNewsImage(imageUrl);
            }
            service.save(news);


            ra.addFlashAttribute("message", "News has been saved successfully");
        } catch (IOException | InvalidNewsException e) {
            ra.addFlashAttribute("error", "Failed to upload image. Please try again.");
        }

        return "redirect:/admin-news";
    }

    @GetMapping("/admin-news/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            News news = service.get(id);
            model.addAttribute("news", news);
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("pageTitle", "Edit Article");

            return "Admin/admin-add-news";
        } catch (NewsNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/404";
        }
    }

    @GetMapping("/admin-news/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The news ID " + id + " has been deleted");
        } catch (NewsNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin-news";
    }
}
