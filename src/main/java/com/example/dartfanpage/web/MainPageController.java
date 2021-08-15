package com.example.dartfanpage.web;

import com.example.dartfanpage.news.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@AllArgsConstructor
public class MainPageController {

    private NewsService newsService;

    @GetMapping("/")
    public String displayMainPage(Model model){
        model.addAttribute("activePage", "main");
        model.addAttribute("news", newsService.getAllNews());
        return "main.html";
    }

    @PostMapping("/addNews")
    public String addNews(@RequestParam String author,
                          @RequestParam("imageFile") MultipartFile articlePicture,
                          @RequestParam String title,
                          @RequestParam String headline,
                          @RequestParam String text){

        String response = newsService.saveNews(author, articlePicture, title, headline, text);
        if(response.equals("Article saved")) {
            return "main.html";
        }else {
         return "addArticle";
        }
    }

}
