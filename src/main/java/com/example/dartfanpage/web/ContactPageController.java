package com.example.dartfanpage.web;

import com.example.dartfanpage.config.CompanyInfo;
import com.example.dartfanpage.contact.ContactService;
import com.example.dartfanpage.contact.Email;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class ContactPageController {

    private ContactService contactService;
    private CompanyInfo companyInfo;

    @GetMapping("/contact")
    String displayContactPage(Model model){
        model.addAttribute("info", companyInfo);
        model.addAttribute("activePage", "contact");
        return "contact.html";
    }

    @PostMapping("/sendEmail")
    String sendEmail(@ModelAttribute Email email ,Model model){
        contactService.sendEmail(email);
        model.addAttribute("info", companyInfo);
        model.addAttribute("activePage", "contact");
        model.addAttribute("success", "Wiadomość została wysłana");
        return "contact.html";
    }



}
