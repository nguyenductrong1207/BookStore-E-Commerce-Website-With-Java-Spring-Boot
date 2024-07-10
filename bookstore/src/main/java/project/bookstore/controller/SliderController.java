package project.bookstore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.bookstore.entity.Slider;
import project.bookstore.exception.SliderNotFoundException;
import project.bookstore.exception.TooManySelectedSlidersException;
import project.bookstore.service.CloudinaryService;
import project.bookstore.service.SliderService;

import java.io.IOException;
import java.util.List;

@Controller
public class SliderController {
    @Autowired
    private SliderService sliderService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/admin-sliders")
    public String showSliderAdmin(Model model) {
        List<Slider> sliders = sliderService.getAllSlides();
        model.addAttribute("sliders", sliders);
        model.addAttribute("pageTitle", "Sliders");
        return "Admin/admin-sliders";
    }

    @GetMapping("/admin-add-slider")
    public String showNewForm(Model model) {
        model.addAttribute("slider", new Slider());
        model.addAttribute("pageTitle", "Add New Slider");

        return "Admin/admin-add-slider";
    }

    @PostMapping("admin-add-slider/save")
    public String saveAuthor(@ModelAttribute(name = "slider") Slider slider, RedirectAttributes ra,
                             @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
        boolean isNewSlider = (slider.getId() == null);
        Slider savedSlider = null;
        try {
            savedSlider = sliderService.save(slider);

            if (isNewSlider || !multipartFile.isEmpty()) {
                String imageUrl = cloudinaryService.uploadFile(multipartFile, "Admin/sliders/" + savedSlider.getId());
                savedSlider.setImageUrl(imageUrl);
                sliderService.save(savedSlider);
            }

            if (isNewSlider && multipartFile.isEmpty()) {
                ra.addFlashAttribute("error", "Please select an image for the new slider.");
                return "redirect:/404";
            }
        } catch (TooManySelectedSlidersException e) {
            throw new RuntimeException(e);
        }

        ra.addFlashAttribute("message", "Slider has been saved successfully");
        return "redirect:/admin-sliders";
    }

    @GetMapping("/admin-sliders/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Slider slider = sliderService.get(id);
            model.addAttribute("slider", slider);
            model.addAttribute("pageTitle", "Edit Slider (ID: " + id + ")");

            return "Admin/admin-add-slider";
        } catch (SliderNotFoundException e) {
            ra.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin-sliders";
        }
    }

    @GetMapping("/admin-sliders/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            sliderService.delete(id);
            ra.addFlashAttribute("message", "The slider ID " + id + " has been deleted");
        } catch (SliderNotFoundException e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin-sliders";
    }
}
