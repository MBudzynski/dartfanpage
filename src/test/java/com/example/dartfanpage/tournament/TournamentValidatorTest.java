package com.example.dartfanpage.tournament;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TournamentValidatorTest {

    @Autowired
    private TournamentValidator validator;

    @Test
    void shouldNotAddError() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.isEmpty()).isTrue();
    }

    @Test
    void shouldAddErrorWhenCityIsBlank() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setCity(" ");
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Podanie nazwy miasta jest wymagane");
    }

    @Test
    void shouldAddErrorWhenCityIsNull() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setCity(null);
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Podanie nazwy miasta jest wymagane");
    }

    @Test
    void shouldAddErrorWhenPlaceNameIsNull() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setPlaceName(null);
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Nazwa miejsca jest wymagana. Przynajmniej 3 znaki oraz pierwsza duża, reszta małe");
    }

    @Test
    void shouldAddErrorWhenPlaceNameIsBlank() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setPlaceName("  ");
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Nazwa miejsca jest wymagana. Przynajmniej 3 znaki oraz pierwsza duża, reszta małe");
    }

    @Test
    void shouldAddErrorWhenPlaceNameIsIncorrect() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setPlaceName("Gd");
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Nazwa miejsca jest wymagana. Przynajmniej 3 znaki oraz pierwsza duża, reszta małe");

    }

    @Test
    void shouldAddErrorWhenStreetIsIncorrect() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setStreet("adadd");
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Zła nazwa ulicy");
    }

    @Test
    void shouldAddErrorWhenStreetIsNull() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setStreet(null);
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Zła nazwa ulicy");
    }

    @Test
    void shouldAddErrorWhenStreetIsBlank() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setStreet("  ");
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Zła nazwa ulicy");
    }


    @Test
    void shouldAddErrorWhenCityIsIncorrect() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setCity("hgfcf");
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Podanie nazwy miasta jest wymagane");
    }


    @Test
    void shouldAddErrorWhenVenueNumberIsIncorrect() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setVenueNumber("15Dolna");
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Błędny numer lokalu");
    }

    @Test
    void shouldAddErrorWhenVenueNumberIsBlank() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setVenueNumber("   ");
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Błędny numer lokalu");
    }

    @Test
    void shouldAddErrorWhenVenueNumberIsNull() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setVenueNumber(null);
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Błędny numer lokalu");
    }

    @Test
    void shouldAddErrorWhenZipCodeIsNull() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setZipCode(null);
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Zły format. Kod pocztowy powinien mieć format 12-345");
    }

    @Test
    void shouldAddErrorWhenZipCodeIsIncorrect() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setZipCode("5585-258");
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Zły format. Kod pocztowy powinien mieć format 12-345");
    }

    @Test
    void shouldAddErrorWhenZipCodeIsBlank() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setZipCode(" ");
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Zły format. Kod pocztowy powinien mieć format 12-345");
    }

    @Test
    void shouldAddErrorWhenPostOfficeIsBlank() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setPostOffice(" ");
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Podanie poczty jest wymagane");
    }

    @Test
    void shouldAddErrorWhenPostOfficeIsNull() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setPostOffice(null);
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Podanie poczty jest wymagane");
    }

    @Test
    void shouldAddErrorWhenPostOfficeIsIncorrect() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setPostOffice("LuBlIn");
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Podanie poczty jest wymagane");
    }

    @Test
    void shouldAddErrorWhenDataIsNull() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setData(null);
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Brak daty turnieju");
    }

    @Test
    void shouldAddErrorWhenDataIsBeforeNow() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setData(LocalDate.now().minusDays(5));
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Brak daty turnieju");
    }

    @Test
    void shouldAddErrorWhenStartAtIsNull() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setStartAt(null);
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Brak godziny rozpoczecia");
    }

    @Test
    void shouldAddErrorWhenEntryFeeIsNull() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setEntryFee(null);
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Zły format opłaty wpisowej. Opłata musi być większa od 0.");
    }

    @Test
    void shouldAddErrorWhenEntryFeeIsLowerThan0() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setEntryFee(BigDecimal.valueOf(-5));
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Zły format opłaty wpisowej. Opłata musi być większa od 0.");
    }

    @Test
    void shouldAddErrorWhenEntryFeeIsEqual0() {
        //given
        TournamentDto tournamentDto = populateTournamentDto();
        tournamentDto.setEntryFee(BigDecimal.valueOf(0));
        //when
        Map<String, String> errors = validator.isValid(tournamentDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Zły format opłaty wpisowej. Opłata musi być większa od 0.");
    }



    private TournamentDto populateTournamentDto() {
        TournamentDto tournamentDto = new TournamentDto();
        tournamentDto.setCity("Lublin");
        tournamentDto.setData(LocalDate.now().plusDays(8));
        tournamentDto.setEntryFee(BigDecimal.valueOf(50));
        tournamentDto.setPlaceName("Kotwica");
        tournamentDto.setStreet("Aleje Racławickie");
        tournamentDto.setVenueNumber("5A");
        tournamentDto.setStartAt(LocalTime.now());
        tournamentDto.setPostOffice("Lublin");
        tournamentDto.setZipCode("20-601");
        return tournamentDto;
    }


}