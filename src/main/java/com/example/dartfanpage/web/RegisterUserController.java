package com.example.dartfanpage.web;

import com.example.dartfanpage.users.RegistrationUserDto;
import com.example.dartfanpage.users.RegistrationValidator;
import com.example.dartfanpage.users.UserExistsException;
import com.example.dartfanpage.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller()
public class RegisterUserController {

    @Autowired
    private RegistrationValidator registrationValidator;

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    String registerForm(Model model) {
        model.addAttribute("registrationUserDto", new RegistrationUserDto());
        return "registerPage";
    }

    @PostMapping("/register")
    String registerUser(@ModelAttribute RegistrationUserDto registrationUserDto, Model model) {
        Map<String, String> errorMap = registrationValidator.isValid(registrationUserDto);
        if (errorMap.isEmpty()) {
            try {
                userService.registerUser(registrationUserDto);
            } catch (UserExistsException e) {
                model.addAttribute("registrationUserDto", registrationUserDto);
                model.addAttribute("userFoundMessage", e.getMessage());
                return "registerPage";

            }
            return "main.html";
        }
        model.addAttribute("registrationUserDto", registrationUserDto);
        model.addAllAttributes(errorMap);
        return "registerPage";
    }
}
