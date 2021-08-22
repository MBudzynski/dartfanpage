package com.example.dartfanpage.web;

import com.example.dartfanpage.news.NewsDto;
import com.example.dartfanpage.news.NewsService;
import com.example.dartfanpage.news.comment.CommentDto;
import com.example.dartfanpage.tournament.TournamentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;


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
                          @RequestParam("mainPicture") MultipartFile mainPicture,
                          @RequestParam String title,
                          @RequestParam String headline,
                          @RequestParam String text,
                          @RequestParam("pictures") MultipartFile[] pictures){

        String response = newsService.saveNews(author, mainPicture, title, headline, text, pictures);
        if(response.equals("Article saved")) {
            return "redirect:/";
        }else {
         return "addArticle.html";
        }
    }

    @GetMapping("/news/{id}")
    String displayNews(@PathVariable Long id, Model model){
        Optional<NewsDto> newsById = newsService.findNewsById(id);
        if (newsById.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("news", newsById.get());
        model.addAttribute("activePage", "main");
        return "newsPage.html";
    }

    @PostMapping("/addComment")
    String addComment(@RequestParam Long id,
                      @RequestParam String author,
                      @RequestParam String text){

        NewsDto newsDto = newsService.findNewsById(id).get();
        CommentDto commentDto = CommentDto.builder().author(author).text(text)
                .dataTime(LocalDateTime.now()).news(newsDto).build();
        newsService.addComment(commentDto);

        return "redirect:/news/" + id;
    }

}
