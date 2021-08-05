package com.example.dartfanpage.web;

import com.example.dartfanpage.tournament.TournamentDto;
import com.example.dartfanpage.tournament.TournamentService;
import com.example.dartfanpage.tournament.TournamentValidator;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class TournamentPageController {

    private final TournamentService tournamentService;

    private final TournamentValidator tournamentValidator;

    @GetMapping("/tournaments")
    String tournamentsList(Model model){
        model.addAttribute("tournaments", tournamentService.getCurrentTournaments());
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
                      @RequestParam String venueNumber,
                      @RequestParam String zipCode,
                      @RequestParam String postOffice,
                      @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                      @RequestParam LocalTime startAt,
                      @RequestParam BigDecimal entryFee,
                               Model model) {

        TournamentDto dto = new TournamentDto(id, placeName, city, street, venueNumber, zipCode, postOffice,
                data, startAt, entryFee);

        Map<String, String> errorMap = tournamentValidator.isValid(dto);

        if(errorMap.isEmpty()){
            if (id == null) {
                tournamentService.addTournament(dto);
            } else {
                tournamentService.update(dto);
            }
            return "redirect:/tournaments";
        } else {
            model.addAttribute("activePage", "tournaments");
            model.addAttribute("tournament", dto);
            model.addAttribute(errorMap);
            return "addEditTournament.html";
        }

    }

    @GetMapping("/tournaments/{id}")
    String editTournament(@PathVariable Long id, Model model) {
        Optional<TournamentDto> tournamentById = tournamentService.findTournamentById(id);
        if (tournamentById.isEmpty()) {
            return "redirect:/tournaments";
        }
        model.addAttribute("tournament", tournamentById.get());
        model.addAttribute("activePage", "tournaments");
        return "addEditTournament.html";
    }

    @GetMapping("/tournamentDelete/{id}")
    String deleteTournament(@PathVariable Long id) {
        tournamentService.delete(id);
        return "redirect:/tournaments";
    }


}
