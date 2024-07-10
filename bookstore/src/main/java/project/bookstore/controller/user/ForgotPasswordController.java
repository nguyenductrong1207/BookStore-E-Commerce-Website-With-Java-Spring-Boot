package project.bookstore.controller.user;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.entity.user.User;
import project.bookstore.service.UserService;
import project.bookstore.utils.Utility;

import java.io.UnsupportedEncodingException;

@Controller
public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserService userService;

    @PostMapping("/forget-password")
    public String processForgotPassword(@AuthenticationPrincipal CustomUserDetails userDetails,
                                        HttpServletRequest request, RedirectAttributes ra) {
        String token = RandomStringUtils.randomAlphanumeric(5, 20);
        String currentURI = request.getRequestURI();
        String email = (currentURI.equals("/profile"))?userDetails.getUsername():request.getParameter("forgotEmail");

        try {
            userService.updateResetPasswordToken(token, email);

            String resetPasswordLink = Utility.getSiteURL(request) + "/reset-password?token=" + token;
            sendMail(email, resetPasswordLink);
        } catch (UnsupportedEncodingException | MessagingException e) {
            ra.addAttribute("error", "Error while sending email");
        }
        ra.addFlashAttribute("message","We have send you an email. Please check it");

        if (currentURI.equals("/profile")){
            return "redirect:/profile";
        }

        return "redirect:/";
    }

    @GetMapping("/reset-password")
    public String showForgetPasswordForm(@Param(value = "token") String token, RedirectAttributes ra, Model model) {
        User user = userService.getUserByResetPasswordToken(token);
        model.addAttribute("token", token);
        model.addAttribute("title", "Change Your Password");

        if (user == null){
            ra.addFlashAttribute("error", "Invalid Token");
        }

        return "Client/reset-password";
    }

    @PostMapping("/reset-password")
    public String processForgetPassword(HttpServletRequest request, RedirectAttributes ra) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String password = request.getParameter("password");

        if(!(authentication == null || authentication instanceof AnonymousAuthenticationToken)){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String newPassword = encoder.encode(password);
            CustomUserDetails userDetails = (CustomUserDetails)  authentication.getPrincipal();
            User user  = userDetails.getUser();

            userService.updatePassword(newPassword, user.getEmail());

            return "redirect:/profile";
        }
        String token = request.getParameter("token");
        User user = userService.getUserByResetPasswordToken(token);

        if (user == null){
            ra.addFlashAttribute("error", "Invalid Token");
        }else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String newPassword = encoder.encode(password);

            System.out.println(password);
            userService.updatePassword(newPassword, user.getEmail());

            ra.addFlashAttribute("message","Change password successfully");
        }

        if (request.getRequestURI().equals("/profile")){
            return "redirect:/profile";
        }

        return "redirect:/client-login";
    }

    public void sendMail(String receiver, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String subject = "[Bookland] Reset Your Password";
        String content = "<p>Hello,</p>"
                + "<br>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Please accept the email and</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\" target=\""+"_blank"+"\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setFrom("contact@bookland.com", "Bookland Support");
        helper.setTo(receiver);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }
}
