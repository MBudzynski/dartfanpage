package com.example.dartfanpage.web;

import com.example.dartfanpage.news.NewsDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class GameRolesController {

    @GetMapping("/gameRoles")
    String displayAddArticlePage(Model model) {
        model.addAttribute("activePage", "gameRoles");
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        return "gameRoles.html";
    }
}
