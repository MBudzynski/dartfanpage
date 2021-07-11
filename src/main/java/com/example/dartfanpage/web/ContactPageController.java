package com.example.dartfanpage.web;

import com.example.dartfanpage.config.CompanyInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class ContactPageController {

    private CompanyInfo companyInfo;

    @GetMapping("/contact")
    ModelAndView displayContactPage(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("info", companyInfo);
        return mav;
    }

}
