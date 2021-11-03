package com.example.dartfanpage.web;

import com.example.dartfanpage.tournament.Tournament;
import com.example.dartfanpage.tournament.TournamentDto;
import com.example.dartfanpage.tournament.TournamentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class TournamentPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TournamentRepository repository;

    @Test
    @Transactional
    void shouldGetAllCurrentTournaments() throws Exception {
        //given
        Tournament newTournament = populateTournament();
        repository.save(newTournament);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/tournaments"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(view().name("tournament.html"))
                .andExpect(model().attributeExists("tournaments"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        //than
        List<TournamentDto> list = objectMapper.convertValue(mvcResult.getModelAndView().getModel().get("tournaments"), new TypeReference<List<TournamentDto>>(){});
        TournamentDto tournamentDto =list.stream().filter(tour-> tour.getId().equals(newTournament.getId())).findFirst().get();
        assertThat(tournamentDto).isNotNull();
        assertThat(tournamentDto.getId()).isEqualTo(newTournament.getId());
        assertThat(tournamentDto.getCity()).isEqualTo(newTournament.getCity());
        assertThat(tournamentDto.getPostOffice()).isEqualTo(newTournament.getPostOffice());
        assertThat(tournamentDto.getZipCode()).isEqualTo(newTournament.getZipCode());
        assertThat(tournamentDto.getEntryFee()).isEqualTo(newTournament.getEntryFee());
    }

    @Test
    @Transactional
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void shouldReturnTournamentsPageWhenNotFoundTournamentToEdit() throws Exception {
        //given
        Tournament newTournament = populateTournament();
        repository.save(newTournament);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/tournaments/" + newTournament.getId()+100L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tournaments"))
                .andReturn();
        //than
    }

    @Test
    @Transactional
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void shouldGetShowTournamentToEditPageTournament() throws Exception {
        //given
        Tournament newTournament = populateTournament();
        repository.save(newTournament);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/tournaments/" + newTournament.getId().toString()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(view().name("addEditTournament.html"))
                .andExpect(model().attributeExists("tournament"))
                .andReturn();
        //than
        TournamentDto tournament = objectMapper.convertValue(mvcResult.getModelAndView().getModel().get("tournament"), TournamentDto.class);
        assertThat(tournament).isNotNull();
        assertThat(tournament.getId()).isEqualTo(newTournament.getId());
        assertThat(tournament.getCity()).isEqualTo(newTournament.getCity());
        assertThat(tournament.getPostOffice()).isEqualTo(newTournament.getPostOffice());
        assertThat(tournament.getZipCode()).isEqualTo(newTournament.getZipCode());
        assertThat(tournament.getEntryFee()).isEqualTo(newTournament.getEntryFee());

    }

    @Test
    @Transactional
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void shouldDeleteTournament() throws Exception {
        //given
        Tournament newTournament = populateTournament();
        repository.save(newTournament);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/tournamentDelete/" + newTournament.getId().toString()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(redirectedUrl("/tournaments"))
                .andReturn();
        //than
        Optional<Tournament> tournament = repository.findById(newTournament.getId());
        assertThat(tournament.isPresent()).isFalse();
    }

    @Test
    @Transactional
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void shouldAddNewTournamentToRepository() throws Exception {
        //given
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/tournaments")
                        .param("placeName", "Lotka")
                        .param("city","Lublin")
                        .param("street","Zamojska")
                        .param("venueNumber","5A")
                        .param("zipCode","20-601")
                        .param("postOffice","Lublin")
                        .param("data", LocalDate.now().plusDays(10L).format(DateTimeFormatter.ISO_DATE))
                        .param("startAt", LocalTime.now().toString())
                        .param("entryFee", "150"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(redirectedUrl("/tournaments"))
                .andReturn();
        //than
    }

    @Test
    @Transactional
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void shouldUpdateExistedTournament() throws Exception {
        //given
        Tournament tournament = populateTournament();
        repository.save(tournament);
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/tournaments")
                        .param("id", tournament.getId().toString())
                        .param("placeName", "Lotka")
                        .param("city", "Lublin")
                        .param("street", "Puławska")
                        .param("venueNumber", "10")
                        .param("zipCode", "20-601")
                        .param("postOffice", "Lublin")
                        .param("data", LocalDate.now().plusDays(10L).format(DateTimeFormatter.ISO_DATE).toString())
                        .param("startAt", LocalTime.now().toString())
                        .param("entryFee", "150"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(redirectedUrl("/tournaments"))
                .andReturn();
        //than

    }


    @Test
    @Transactional
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void shouldReturnTheSamePageIfErrorMapHaveError() throws Exception {
        //given
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/tournaments")
                        .param("placeName", "Lotka")
                        .param("city", "Lublin")
                        .param("street", "puławska")
                        .param("venueNumber", "10")
                        .param("zipCode", "-601")
                        .param("postOffice", "Lublin")
                        .param("data", LocalDate.now().plusDays(10L).format(DateTimeFormatter.ISO_DATE).toString())
                        .param("startAt", LocalTime.now().toString())
                        .param("entryFee", "150"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(view().name("addEditTournament.html"))
                .andExpect(model().attributeExists("tournament"))
                .andReturn();
        //than
        String streetError= objectMapper.convertValue(mvcResult.getModelAndView().getModel().get("streetError"), String.class);
        String zipCodeError= objectMapper.convertValue(mvcResult.getModelAndView().getModel().get("zipCodeError"), String.class);
        assertThat(streetError).isEqualTo("Zła nazwa ulicy");
        assertThat(zipCodeError).isEqualTo("Zły format. Kod pocztowy powinien mieć format 12-345");
    }

    private Tournament populateTournament() {
        Tournament tournament = new Tournament();
        tournament.setCity("Lublin");
        tournament.setData(LocalDate.now().plusDays(8));
        tournament.setEntryFee(BigDecimal.valueOf(50));
        tournament.setPlaceName("Kotwica");
        tournament.setStreet("Aleje Racławickie");
        tournament.setVenueNumber("5A");
        tournament.setStartAt(LocalTime.now());
        tournament.setPostOffice("Lublin");
        tournament.setZipCode("20-601");
        return tournament;
    }




}