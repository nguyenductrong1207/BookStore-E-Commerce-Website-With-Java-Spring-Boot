package project.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.bookstore.entity.Publisher;
import project.bookstore.exception.PublisherNotFoundException;
import project.bookstore.service.PublisherService;

import java.util.List;

@Controller
public class PublisherController {
    @Autowired
    private PublisherService service;

    @GetMapping("/admin-publisher")
    public String showPublishers(Model model) {
        List<Publisher> listPublishers = service.getAllPublisher();
        model.addAttribute("listPublisher", listPublishers);
        model.addAttribute("pageTitle", "Publisher List");
        return "Admin/admin-publisher";
    }

    @GetMapping("/admin-add-publisher")
    public String showNewForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        model.addAttribute("pageTitle", "Add New Publisher");

        return "Admin/admin-add-publisher";
    }

    @PostMapping("/admin-add-publisher/save")
    public String savePublisher(Publisher publisher, RedirectAttributes ra) {
        service.save(publisher);
        ra.addFlashAttribute("message", "Publisher has been saved successfully");
        return "redirect:/admin-publisher";
    }

    @GetMapping("/admin-publisher/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Publisher publisher = service.get(id);
            model.addAttribute("publisher", publisher);
            model.addAttribute("pageTitle", "Edit publisher (ID: " + id + ")");

            return "Admin/admin-add-publisher";
        } catch (PublisherNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin-publisher";
        }
    }

    @GetMapping("/admin-publisher/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        service.deleteById(id);
        ra.addFlashAttribute("message", "The publisher ID " + id + " has been deleted");
        return "redirect:/admin-publisher";
    }
}
