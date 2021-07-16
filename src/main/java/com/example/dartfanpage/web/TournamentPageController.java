package com.example.dartfanpage.web;

import com.example.dartfanpage.tournament.TournamentDto;
import com.example.dartfanpage.tournament.TournamentService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

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

    @GetMapping("/addEditTournament")
    String displayAddEditTournamentPage(Model model){
        model.addAttribute("activePage", "tournaments");
        return "addEditTournament.html";
    }

    @PostMapping("/tournaments")
    String addTournament(@RequestParam String placeName,
                      @RequestParam String city,
                      @RequestParam String street,
                      @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                      @RequestParam LocalTime startAt,
                      @RequestParam BigDecimal entryFee) {

        TournamentDto dto = new TournamentDto(placeName, city, street, data, startAt,entryFee);
        tournamentService.addTournament(dto);
        return "redirect:/tournaments";
    }

}
