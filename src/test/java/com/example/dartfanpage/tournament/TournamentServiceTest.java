package com.example.dartfanpage.tournament;

import com.example.dartfanpage.tournament.Tournament;
import com.example.dartfanpage.tournament.TournamentDto;
import com.example.dartfanpage.tournament.TournamentRepository;
import com.example.dartfanpage.tournament.TournamentService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
class TournamentServiceTest {

    @Autowired
    private TournamentService service;

    @Autowired
    private TournamentRepository repository;

    @BeforeEach
    public void cleanDb() {
        repository.deleteAll();
    }

    @Test
    void shouldReturnEmptyList(){
        //when
        List<TournamentDto> currentTournaments = service.getCurrentTournaments();
        //than
        Assertions.assertTrue(currentTournaments.isEmpty());
    }

    @Test
    void shouldRemoveOneExpiredTournament(){
        //given
        Tournament tournament1 = new Tournament(null, "Zamek", "Lublin", "ul. Zamkowa",
                LocalDate.now().plusDays(5), LocalTime.now(), new BigDecimal(100));
        Tournament tournament2 = new Tournament(null, "Browar", "Poznań", "ul. Browarna",
                LocalDate.now().plusDays(5), LocalTime.now(), new BigDecimal(100));
        Tournament tournament3 = new Tournament(null, "Karczma", "Warszawa", "ul. Pogodna",
                LocalDate.now().minusDays(5), LocalTime.now(), new BigDecimal(150));
        //when
        repository.saveAll(Lists.list(tournament1, tournament2, tournament3));
        List<TournamentDto> currentTournaments = service.getCurrentTournaments();
        //than
        Assertions.assertEquals(2, currentTournaments.size());
        Assertions.assertTrue(currentTournaments.stream().allMatch(tournament -> tournament.getData().isAfter(LocalDate.now())));
    }

    @Test
    void shouldPersistTournamentInDb(){
        //given
        Tournament tournament1 = new Tournament(null, "Zamek", "Lublin", "ul. Zamkowa",
                LocalDate.now().plusDays(5), LocalTime.now(), new BigDecimal(100));
        //when
        service.addTournament(tournament1.toDto());
        //than
        List<Tournament> tournaments = repository.findAll();
        Assertions.assertEquals(1, tournaments.size());
        Assertions.assertEquals("Zamek", tournaments.get(0).getPlaceName());
        Assertions.assertEquals("Lublin", tournaments.get(0).getCity());
        Assertions.assertEquals("ul. Zamkowa", tournaments.get(0).getStreet());
        Assertions.assertEquals(LocalDate.now().plusDays(5), tournaments.get(0).getData());
        Assertions.assertEquals(new BigDecimal(100).setScale(2, RoundingMode.HALF_UP), tournaments.get(0).getEntryFee());
    }

}
