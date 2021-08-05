package com.example.dartfanpage.tournament;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TournamentValidator {

    public Map<String, String> isValid(TournamentDto dto) {
        Map<String, String> errors = new HashMap<>();

        if (dto.getPlaceName() == null || !dto.getPlaceName().matches("")) {
            errors.put("placeNameError", "Nazwa miejsca jest wymagana. Przynajmniej 3 znaki oraz pierwsza duża, reszta małe");
        }
        if (dto.getCity() == null || !dto.getCity().matches("^[A-Z][a-z]{2,}( [A-Z][a-z]{2,})?$")) {
            errors.put("cityError", "Podanie nazwy miasta jest wymagane");
        }
        if (dto.getStreet() == null || !dto.getStreet().matches("^[A-Z][a-z]{2,}(( [A-Z|a-z][a-z]{1,})+)?$")) {
            errors.put("streetError", "Zła nazwa ulicy");
        }
        if (dto.getVenueNumber() == null || !dto.getVenueNumber().matches("")) {
            errors.put("venueNumberError", "Błędny numer lokalu");
        }
        if (dto.getZipCode() == null || !dto.getZipCode().matches("^[0-9]{2}(-[0-9]{3})$")) {
            errors.put("zipCodeError", "Zły format. Kod pocztowy powinien mieć format 12-345");
        }
        if (dto.getPostOffice() == null || dto.getPostOffice().matches("")) {
            errors.put("postOfficeError", "Podanie poczty jest wymagane");
        }
        if (dto.getData() == null || !dto.getData().toString().matches("^(19|20)[0-9]{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[0-1])$")) {
            errors.put("dateError", "Zły format. Data powinna być podana w formacie RRRR-MM-DD");
        }
        if (dto.getStartAt() == null || !dto.getStartAt().toString().matches("")) {
            errors.put("startAtError", "Zły format godziny rozpoczecia");
        }
        if (dto.getEntryFee() == null || !dto.getEntryFee().toString().matches("")) {
            errors.put("entryFeeError", "Zły format opłaty wpisowej");
        }


        return errors;
    }

}
