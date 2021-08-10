package com.example.dartfanpage.web;

import com.example.dartfanpage.config.CompanyInfo;
import com.example.dartfanpage.contact.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


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



}
