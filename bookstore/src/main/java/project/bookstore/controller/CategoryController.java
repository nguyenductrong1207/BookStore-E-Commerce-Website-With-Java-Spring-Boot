package project.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.bookstore.entity.Category;
import project.bookstore.exception.CategoryNotFoundException;
import project.bookstore.service.CategoryService;

@Controller
public class CategoryController {
    @Autowired
    CategoryService service;

    @GetMapping("/admin-category")
    public String showBookCategory(Model model) {
        List<Category> listCategories = service.getAllCategories();
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("pageTitle", "Category List");
        return "Admin/admin-category";
    }

    @GetMapping("/admin-add-category")
    public String showNewForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Add Category");
        return "Admin/admin-add-category";
    }

    @PostMapping("/admin-add-category/save")
    public String saveCategory(Category category, RedirectAttributes ra) {
        service.save(category);
        ra.addFlashAttribute("message", "The category has been saved successfully");
        return "redirect:/admin-category";
    }

    @GetMapping("/admin-category/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Category category = service.get(id);
            category.setId(id);
            model.addAttribute("category", category);
            model.addAttribute("pageTitle", "Edit Category (ID: " + id + ")");
            return "Admin/admin-add-category";

        } catch (CategoryNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin-category";
        }
    }

    @GetMapping("/admin-category/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The category ID " + id + " has been deleted");
        } catch (CategoryNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin-category";
    }
}
