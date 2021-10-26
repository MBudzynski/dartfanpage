package com.example.dartfanpage.tournament;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class TournamentValidator {

    public Map<String, String> isValid(TournamentDto dto) {
        Map<String, String> errors = new HashMap<>();

        if (dto.getPlaceName() == null || !dto.getPlaceName().matches("([A-ZŃŚĆŻŹĄĘÓŁa-zńśćżźąęół]{3,})( [a-zńśćżźąęółA-ZŃŚĆŻŹĄĘÓŁ0-9]{1,})*")) {
            errors.put("placeNameError", "Nazwa miejsca jest wymagana. Przynajmniej 3 znaki oraz pierwsza duża, reszta małe");
        }
        if (dto.getCity() == null || !dto.getCity().matches("^[A-ZŃŚĆŻŹĄĘÓŁ][a-zńśćżźąęół]{2,}( [A-ZŃŚĆŻŹĄĘÓŁ][a-zńśćżźąęół]{2,})?$")) {
            errors.put("cityError", "Podanie nazwy miasta jest wymagane");
        }
        if (dto.getStreet() == null || !dto.getStreet().matches("^[A-ZŃŚĆŻŹĄĘÓŁ][a-zńśćżźąęół]{2,}(( [A-ZŃŚĆŻŹĄĘÓŁa-zńśćżźąęół][a-zńśćżźąęół]{2,})+)?$")) {
            errors.put("streetError", "Zła nazwa ulicy");
        }
        if (dto.getVenueNumber() == null || !dto.getVenueNumber( ).matches("(^[0-9]{1,})([A-ZŃŚĆŻŹĄĘÓŁa-zńśćżźąęół])?(/([0-9]{0,3}))?")) {
            errors.put("venueNumberError", "Błędny numer lokalu");
        }
        if (dto.getZipCode() == null || !dto.getZipCode().matches("^[0-9]{2}(-[0-9]{3})$")) {
            errors.put("zipCodeError", "Zły format. Kod pocztowy powinien mieć format 12-345");
        }
        if (dto.getPostOffice() == null  || !dto.getPostOffice().matches("^([A-ZŃŚĆŻŹĄĘÓŁ][a-zńśćżźąęół]{2,}[ ]?){1,}")) {
            errors.put("postOfficeError", "Podanie poczty jest wymagane");
        }
        if (dto.getData() == null || dto.getData().isBefore(LocalDate.now()) ) {
            errors.put("dateError", "Brak daty turnieju");
        }
        if (dto.getStartAt() == null ) {
            errors.put("startAtError", "Brak godziny rozpoczecia");
        }
        if (dto.getEntryFee() == null || dto.getEntryFee().intValue() == 0 || !dto.getEntryFee().toString().matches("[0-9]{1,}")) {
            errors.put("entryFeeError", "Zły format opłaty wpisowej. Opłata musi być większa od 0.");
        }


        return errors;
    }

}
