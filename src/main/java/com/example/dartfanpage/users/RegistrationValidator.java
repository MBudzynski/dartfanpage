package com.example.dartfanpage.users;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegistrationValidator {

    public Map<String, String> isValid(RegistrationUserDto dto) {
        Map<String, String> errors = new HashMap<>();

        if (dto.getFirstName() == null || !dto.getFirstName().matches("^[A-Z][a-z]{2,}$")) {
            errors.put("firstNameError", "Imię jest wymagane. Przynajmniej 3 znaki oraz pierwsza duża, reszta małe");
        }
        if (dto.getLastName() == null || !dto.getLastName().matches("^[A-Z][a-z]{2,}(-[A-Z][a-z]{2,})?$")) {
            errors.put("firstNameError", "Nazwisko jest wymagane. Przynajmniej 3 znaki oraz pierwsza duża, reszta małe");
        }
        if (dto.getEMail() == null || !dto.getEMail().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
            errors.put("eMailError", "Zły format adresu email");
        }
        if (dto.getPassword() == null || !dto.getPassword().matches("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{10,20})")) {
            errors.put("passwordError", "Hasło jest wymagane. Musi zawierać od 10 do 20 znaków oraz jedna duża, jedna mała litere i jedna cyfra.");
        }
        if (dto.getCity() == null || !dto.getCity().matches("^[A-Z][a-z]{2,}( [A-Z][a-z]{2,})?$")) {
            errors.put("cityError", "Podanie nazwy miasta jest wymagane");
        }
        if (dto.getZipCode() == null || !dto.getZipCode().matches("^[0-9]{2}(-[0-9]{3})$")) {
            errors.put("zipCodeError", "Zły format. Kod pocztowy powinien mieć format 12-345");
        }
        if (dto.getStreet() == null || !dto.getStreet().matches("^[A-Z][a-z]{2,}(( [A-Z|a-z][a-z]{1,})+)?$")) {
            errors.put("streetError", "Podanie nazwy ulicy jest wymagane.");
        }
        if (dto.getBirthDate() == null || !dto.getBirthDate().matches("^(19|20)[0-9]{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[0-1])$")) {
            errors.put("birthDateError", "Zły format. Data urodzin powinna być podana w formacie RRRR-MM-DD");
        }
        if (dto.getPhone() == null || !dto.getPhone().matches("^((\\+[48]{2}( ))?)(([0-9]{3})( |-[0-9]{3})( |-[0-9]{3})|[0-9]{9})$")) {
            errors.put("phoneError", "Zły format. Numer telefonu powinien składać się z 9 cyfr i mieć format xxx xxx xxx lub xxx-xxx-xxx");
        }


        return errors;
    }

}
