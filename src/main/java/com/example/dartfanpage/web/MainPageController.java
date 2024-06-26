package com.example.dartfanpage.web;

import com.example.dartfanpage.news.NewsDto;
import com.example.dartfanpage.news.NewsService;
import com.example.dartfanpage.news.NewsValidator;
import com.example.dartfanpage.news.comment.CommentDto;
import com.example.dartfanpage.news.comment.CommentValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;



@Controller
@AllArgsConstructor
public class MainPageController {

    private NewsService newsService;
    private CommentValidator commentValidator;
    private NewsValidator newsValidator;

    @GetMapping("/")
    public String displayMainPage(Model model){
        model.addAttribute("news", newsService.getAllNews());
        addAttributesToModel(model);
        return "main.html";
    }

    @GetMapping("/addArticle")
    @PreAuthorize("hasRole('ADMIN')")
    String displayAddArticlePage(Model model) {
        NewsDto newsDto = new NewsDto();
        model.addAttribute("activePage", "main");
        model.addAttribute("news", newsDto);
        return "addArticle.html";
    }

    @PostMapping("/addArticle")
    @PreAuthorize("hasRole('ADMIN')")
    public String addNews(@RequestParam String author,
                          @RequestParam("mainPicture") MultipartFile mainPicture,
                          @RequestParam String title,
                          @RequestParam String headline,
                          @RequestParam String text,
                          @RequestParam("pictures") MultipartFile[] pictures, Model model){

        NewsDto newsDto = NewsDto.builder()
                .author(author)
                .title(title)
                .publicationDate(LocalDate.now())
                .mainPicture(mainPicture.getOriginalFilename())
                .headline(headline)
                .text(text)
                .build();

        Map<String, String> newsError = newsValidator.isValid(newsDto);
        if(newsError.isEmpty()){
            String response = newsService.saveNews(newsDto, mainPicture,  pictures);
            if(response.equals("Article saved")) {
                return "redirect:/";
            }else {
                return "addArticle.html";
            }
        }
        addAttributesToModel(model);
        model.addAllAttributes(newsError);
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("news", newsDto);
        return "addArticle.html";
    }

    @GetMapping("/news/{id}")
    String displayNews(@PathVariable Long id, Model model){
        Optional<NewsDto> newsById = newsService.findNewsById(id);
        if (newsById.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("news", newsById.get());
        addAttributesToModel(model);
        model.addAttribute("text", "");
        return "newsPage.html";
    }

    @PostMapping("/addComment")
    @PreAuthorize("isAuthenticated()")
    String addComment(@RequestParam Long id,
                      @RequestParam String text, Model model){
        Map<String, String> commentError = commentValidator.isValid(text);
        if(commentError.isEmpty()){
            CommentDto commentDto = CommentDto.builder().author(SecurityContextHolder.getContext().getAuthentication().getName()).text(text)
                    .dataTime(LocalDateTime.now()).build();
            newsService.addComment(commentDto,id);

            return "redirect:/news/" + id;
        }
        model.addAllAttributes(commentError);
        model.addAttribute("news", newsService.findNewsById(id).get());
        addAttributesToModel(model);
        model.addAttribute("text", "");
        return "newsPage.html";
    }


    @GetMapping("/logError")
    public String displayLoginErrorPage(Model model){
        model.addAttribute("loginError", "Invalid username or password");
        model.addAttribute("news", newsService.getAllNews());
        addAttributesToModel(model);
        return "main.html";
    }

    private Model addAttributesToModel(Model model){
        model.addAttribute("activePage", "main");
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        return model;
    }
}
