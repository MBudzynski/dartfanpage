package com.example.dartfanpage.web;

import com.example.dartfanpage.tournament.Tournament;
import com.example.dartfanpage.tournament.TournamentDto;
import com.example.dartfanpage.tournament.TournamentService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

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
    String displayAddEditTournamentPage(Model model, TournamentDto dto){
        model.addAttribute("activePage", "tournaments");
        model.addAttribute("tournament", dto);
        return "addEditTournament.html";
    }

    @PostMapping("/tournaments")
    String addOrEditTournament(@RequestParam(required=false) Long id,
                      @RequestParam String placeName,
                      @RequestParam String city,
                      @RequestParam String street,
                      @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                      @RequestParam LocalTime startAt,
                      @RequestParam BigDecimal entryFee) {

        TournamentDto dto = new TournamentDto(id, placeName, city, street, data, startAt, entryFee);

        if (id == null) {
            tournamentService.addTournament(dto);
        } else {
            tournamentService.update(dto);
        }
        return "redirect:/tournaments";
    }

    @GetMapping("/tournaments/{id}")
    String editTournament(@PathVariable Long id, Model model) throws ParseException {
        Optional<TournamentDto> tournamentById = tournamentService.findTournamentById(id);
        if (tournamentById.isEmpty()) {
            return "redirect:/tournaments";
        }
        model.addAttribute("tournament", tournamentById.get());
        model.addAttribute("activePage", "tournaments");
        return "addEditTournament.html";
    }

}
