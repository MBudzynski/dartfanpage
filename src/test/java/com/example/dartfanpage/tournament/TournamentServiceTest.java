package com.example.dartfanpage.tournament;

import com.example.dartfanpage.exception.NoExistException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
    @Transactional
    void shouldGetSingleTournament(){
        //given
        Tournament tournament = populateTournament();
        //when
        TournamentDto singleTournamentDto= service.findTournamentById(tournament.getId()).get();
        //then
        assertThat(singleTournamentDto).isNotNull();
        assertThat(singleTournamentDto.getId()).isEqualTo(tournament.getId());
        assertThat(singleTournamentDto.getData()).isEqualTo(tournament.getData());
        assertThat(singleTournamentDto.getVenueNumber()).isEqualTo(tournament.getVenueNumber());
        assertThat(singleTournamentDto.getZipCode()).isEqualTo(tournament.getZipCode());
        assertThat(singleTournamentDto.getPostOffice()).isEqualTo(tournament.getPostOffice());
        assertThat(singleTournamentDto.getPlaceName()).isEqualTo(tournament.getPlaceName());
        assertThat(singleTournamentDto.getStartAt()).isEqualTo(tournament.getStartAt());
        assertThat(singleTournamentDto.getStreet()).isEqualTo(tournament.getStreet());
        assertThat(singleTournamentDto.getEntryFee()).isEqualTo(tournament.getEntryFee());
    }

    @Test
    @Transactional
    void shouldAddTournament(){
        //given
        Tournament tournament = new Tournament();
        tournament.setCity("Lublin");
        tournament.setData(LocalDate.now().plusDays(10));
        tournament.setEntryFee(BigDecimal.valueOf(510));
        tournament.setPlaceName("Zamek");
        tournament.setStreet("Aleje Kraśnickie");
        tournament.setVenueNumber("10A");
        tournament.setStartAt(LocalTime.now());
        tournament.setPostOffice("Puławy");
        tournament.setZipCode("20-601");
        Tournament tournament2 = new Tournament();
        tournament2.setCity("Lublin");
        tournament2.setData(LocalDate.now().plusDays(8));
        tournament2.setEntryFee(BigDecimal.valueOf(50));
        tournament2.setPlaceName("Zamek");
        tournament2.setStreet("Aleje Racławickie");
        tournament2.setVenueNumber("5A");
        tournament2.setStartAt(LocalTime.now());
        tournament2.setPostOffice("Lublin");
        tournament2.setZipCode("20-601");
        //when
        service.addTournament(tournament2.toDto());
        List<TournamentDto> tournamentsBeforeAddTournaments= service.getCurrentTournaments();
        service.addTournament(tournament.toDto());
        List<TournamentDto> tournamentsAfterAddTournaments= service.getCurrentTournaments();
        //then
        assertThat(tournamentsBeforeAddTournaments.size()+1).isEqualTo(tournamentsAfterAddTournaments.size());
    }

    @Test
    @Transactional
    void shouldDeleteTournament(){
        //given
        Tournament tournament = populateTournament();
        //when
        service.delete(tournament.getId());
        Optional<TournamentDto> singleTournamentDto= service.findTournamentById(tournament.getId());
        //then
        assertThat(singleTournamentDto.isPresent()).isFalse();
    }

    @Test
    @Transactional
    void shouldThrowNotExistExceptionWhenTournamentWithIdNotExist(){
        //given
        Tournament tournament = populateTournament();
        //when
        NoExistException noExistException = Assertions.assertThrows(NoExistException.class, () -> service.delete(tournament.getId()+10L));
        //then
        assertThat(noExistException.getMessage()).isEqualTo(String.format("Object with id %d not exist", tournament.getId()+10L));
    }

    @Test
    @Transactional
    void shouldUpdateTournament(){
        //given
        Tournament tournament = populateTournament();
        tournament.setPlaceName("9 LOTKA");
        tournament.setCity("Warszawa");
        tournament.setEntryFee(BigDecimal.valueOf(150));
        //when
        service.update(tournament.toDto());
        TournamentDto singleTournamentDto= service.findTournamentById(tournament.getId()).get();
        //then
        assertThat(singleTournamentDto).isNotNull();
        assertThat(singleTournamentDto.getId()).isEqualTo(tournament.getId());
        assertThat(singleTournamentDto.getData()).isEqualTo(tournament.getData());
        assertThat(singleTournamentDto.getVenueNumber()).isEqualTo(tournament.getVenueNumber());
        assertThat(singleTournamentDto.getZipCode()).isEqualTo(tournament.getZipCode());
        assertThat(singleTournamentDto.getPostOffice()).isEqualTo(tournament.getPostOffice());
        assertThat(singleTournamentDto.getPlaceName()).isEqualTo("9 LOTKA");
        assertThat(singleTournamentDto.getStartAt()).isEqualTo(tournament.getStartAt());
        assertThat(singleTournamentDto.getCity()).isEqualTo("Warszawa");
        assertThat(singleTournamentDto.getEntryFee()).isEqualTo(BigDecimal.valueOf(150));
    }

    @Test
    @Transactional
    void shouldThrowExceptionWhenNotFoundTournamentToUpdate(){
        //given
        TournamentDto tournamentDto = populateTournament().toDto();
        tournamentDto.setPlaceName("9 LOTKA");
        tournamentDto.setCity("Warszawa");
        tournamentDto.setEntryFee(BigDecimal.valueOf(150));
        tournamentDto.setId(tournamentDto.getId()+10L);
        //when
        NoExistException noExistException = Assertions.assertThrows(NoExistException.class,
                () -> service.update(tournamentDto));
        //then
        assertThat(noExistException.getMessage()).isEqualTo(String.format("Object with id %d not exist", tournamentDto.getId()));
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
        Tournament tournament1 = new Tournament(null, "Zamek", "Lublin", "Zamkowa", "14a", "22-601", "Lublin",
                LocalDate.now().plusDays(5), LocalTime.now(), new BigDecimal(100));
        Tournament tournament2 = new Tournament(null, "Browar", "Poznań", "ul. Browarna", "89a", "28-601", "Poznań",
                LocalDate.now().plusDays(5), LocalTime.now(), new BigDecimal(100));
        Tournament tournament3 = new Tournament(null, "Karczma", "Warszawa", "ul. Pogodna", "150/86", "03-330", "Warszawa",
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
        Tournament tournament1 = new Tournament(null, "Zamek", "Lublin", "ul. Zamkowa","14a", "22-601", "Lublin",
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

    private Tournament populateTournament(){
        Tournament tournament = new Tournament();
        tournament.setCity("Lublin");
        tournament.setData(LocalDate.now().plusDays(8));
        tournament.setEntryFee(BigDecimal.valueOf(50));
        tournament.setPlaceName("Zamek");
        tournament.setStreet("Aleje Racławickie");
        tournament.setVenueNumber("5A");
        tournament.setStartAt(LocalTime.now());
        tournament.setPostOffice("Lublin");
        tournament.setZipCode("20-601");
        repository.save(tournament);
        return tournament;
    }

}
