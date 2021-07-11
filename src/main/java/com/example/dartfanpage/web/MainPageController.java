package com.example.dartfanpage.web;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor
public class MainPageController {

    @GetMapping("/")
    public String displayMainPage(){
        return "main.html";
    }

}