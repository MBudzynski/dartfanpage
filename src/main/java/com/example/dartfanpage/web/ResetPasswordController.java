package com.example.dartfanpage.web;


import com.example.dartfanpage.users.PasswordValidator;
import com.example.dartfanpage.users.UserService;
import com.example.dartfanpage.users.resetPassword.TokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
@RequiredArgsConstructor
public class ResetPasswordController {

    private final UserService userService;
    private final TokenValidator tokenValidator;
    private final PasswordValidator passwordValidator;

    @GetMapping("/forgetPassword")
    public String displayMainPage(Model model) {
        model.addAttribute("activePage", "main");
        model.addAttribute("success", "");
        return "changePassword.html";
    }

    @PostMapping("/resetPassword")
    public String sendEmailToResetPassword(@RequestParam String email, Model model) {

        userService.sendEmailToResetPassword(email);

        model.addAttribute("activePage", "main");
        model.addAttribute("success", "Email została wysłany. Postepuj zgodnie z instrukcją zawartą w wiadomości.");
        return "changePassword.html";
    }

    @GetMapping("/resetPassword/")
    public String displayChangePasswordPage(Model model, @RequestParam("token") String token) {

        String result = tokenValidator.isValid(token);
        if (result != null) {
            model.addAttribute("password", "");
            model.addAttribute("confirmPassword", "");
            model.addAttribute("errorToken", result);
            return "updatePassword.html";
        } else {
            model.addAttribute("password", "");
            model.addAttribute("confirmPassword", "");
            model.addAttribute("token", token);
            return "updatePassword.html";
        }
    }

    @PostMapping("/changePassword")
    public String changePassword(Model model,
                                 @RequestParam("token") String token,
                                 @RequestParam("password") String password,
                                 @RequestParam("confirmPassword") String confirmPassword) {

        Map<String, String> errors = passwordValidator.isValid(password);

        if (errors.isEmpty()) {
            if (password.equals(confirmPassword)) {
                userService.saveNewPassword(token, password);
                model.addAttribute("activePage", "main");
                model.addAttribute("success", "Hasło pomyślnie zostało zmienione");
                return "changePassword.html";
            } else {
                model.addAttribute("error", "Podane hasła nie są identyczne!!! Spróbuj ponownie.");
                model.addAttribute("password", password);
                model.addAttribute("confirmPassword", "");
                model.addAttribute("token", token);
                return "updatePassword.html";
            }

        } else {
            model.addAttribute("password", password);
            model.addAttribute("confirmPassword", "");
            model.addAttribute("token", token);
            model.addAllAttributes(errors);
            return "updatePassword.html";
        }

    }

}
