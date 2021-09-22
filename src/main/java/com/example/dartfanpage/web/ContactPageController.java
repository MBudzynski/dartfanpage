package com.example.dartfanpage.web;

import com.example.dartfanpage.config.CompanyInfo;
import com.example.dartfanpage.contact.ContactService;
import com.example.dartfanpage.contact.Email;
import com.example.dartfanpage.contact.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;


@Controller
@AllArgsConstructor
public class ContactPageController {

    private ContactService contactService;
    private CompanyInfo companyInfo;
    private EmailValidator emailValidator;

    @GetMapping("/contact")
    String displayContactPage(Model model){
        model.addAttribute("info", companyInfo);
        model.addAttribute("eMail", new Email());
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("activePage", "contact");
        return "contact.html";
    }

    @PostMapping("/sendEmail")
    String sendEmail(@ModelAttribute Email email ,Model model){
        Map<String, String> errors = emailValidator.isValid(email);
        if(errors.isEmpty()){
            contactService.sendEmail(email);
            model.addAttribute("info", companyInfo);
            model.addAttribute("activePage", "contact");
            model.addAttribute("eMail", new Email());
            model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("success", "Wiadomość została wysłana");
            return "contact.html";
        }
        model.addAttribute("info", companyInfo);
        model.addAttribute("activePage", "contact");
        model.addAttribute("eMail", email);
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAllAttributes(errors);
        return "contact.html";
    }



}
