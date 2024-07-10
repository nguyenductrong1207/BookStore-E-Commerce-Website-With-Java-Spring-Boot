package project.bookstore.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.bookstore.entity.Author;
import project.bookstore.exception.AuthorNotFoundException;
import project.bookstore.service.AuthorService;
import project.bookstore.service.CloudinaryService;

@Controller
public class AuthorController {
    @Autowired
    private AuthorService service;
    @Autowired
    private CloudinaryService cloudinaryService;

//    @GetMapping("/admin-author")
//    public String showBookCategory(Model model) {
//        List<Author> authors = service.listAll();
//        model.addAttribute("authors", authors);
//        model.addAttribute("pageTitle", "Author List");
//
//        return "Admin/admin-author";
//    }

    @GetMapping("/admin-add-author")
    public String showNewForm(Model model) {
        Author author = new Author();

        model.addAttribute("author", new Author());
        model.addAttribute("pageTitle", "Add New Author");

        return "Admin/admin-add-author";
    }

    @PostMapping("admin-add-author/save")
    public String saveAuthor(@ModelAttribute(name = "author") Author author, RedirectAttributes ra,
                             @RequestParam("fileImage") MultipartFile multipartFile,
                             @RequestParam("existingImageURL") String existingImageURL) throws IOException {
        if (!multipartFile.isEmpty()) {
            if (existingImageURL != null && !existingImageURL.isEmpty()) {
                cloudinaryService.deleteImage(existingImageURL);
            }

            author.setProfileImageURL(cloudinaryService.uploadFile(multipartFile, "Admin/authors/" + author.getId()));
        } else {
            author.setProfileImageURL(existingImageURL);
        }
        service.save(author);

        ra.addFlashAttribute("message", "Author has been saved successfully");

        return "redirect:/admin-add-book";
    }

    @GetMapping("/admin-author/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Author author = service.get(id);
            model.addAttribute("author", author);
            model.addAttribute("pageTitle", "Edit Author (ID: " + id + ")");
            return "Admin/admin-add-author";
        } catch (AuthorNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin-book";
        }
    }

    @GetMapping("/admin-author/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The author ID " + id + " has been deleted");
        } catch (AuthorNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin-author";
    }
}
