package project.bookstore.controller.global;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.bookstore.exception.NewsNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleGlobalException(Exception ex, RedirectAttributes ra) {
        ra.addFlashAttribute("error", "...");

        return "redirect:/404";
    }

    @ExceptionHandler(NewsNotFoundException.class)
    public String handleNewsNotFoundException(NewsNotFoundException ex, RedirectAttributes ra) {
        ra.addFlashAttribute("error", ex.getMessage());

        return "redirect:/404";
    }
}