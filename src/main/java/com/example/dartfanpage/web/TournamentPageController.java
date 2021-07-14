package com.example.dartfanpage.web;

import com.example.dartfanpage.tournament.TournamentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class TournamentPageController {

    private final TournamentService tournamentService;

    @GetMapping("/tournaments")
    String tournamentsList(Model model){
        model.addAttribute("tournaments", tournamentService.getAllTournaments());
        model.addAttribute("activePage", "tournaments");
        return "tournament.html";
    }

}
