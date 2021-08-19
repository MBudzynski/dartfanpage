package com.example.dartfanpage.web;

import com.example.dartfanpage.news.NewsService;
import com.example.dartfanpage.tournament.TournamentDto;
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
        model.addAttribute("news", newsService.getAllNews());
        model.addAttribute("activePage", "main");
        return "main.html";
    }

    @GetMapping("/addArticle")
    String displayAddEditTournamentPage(Model model, TournamentDto dto) {
        model.addAttribute("activePage", "main");
        return "addArticle.html";
    }

    @PostMapping("/addArticle")
    public String addNews(@RequestParam String author,
                          @RequestParam("articlePicture") MultipartFile articlePicture,
                          @RequestParam String title,
                          @RequestParam String headline,
                          @RequestParam String text){

        String response = newsService.saveNews(author, articlePicture, title, headline, text);
        if(response.equals("Article saved")) {
            return "redirect:/";
        }else {
         return "addArticle.html";
        }
    }

}
