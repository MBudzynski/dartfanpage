package com.example.dartfanpage.contact;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmailValidator {

    public Map<String, String> isValid(Email email) {
        Map<String, String> errors = new HashMap<>();

        if(email.getEMailAddress().isBlank()){
            errors.put("emailAddressError", "Adres do korespondecji jest wymagany");
        }
        if(email.getTitle().isBlank()){
            errors.put("titleError", "Temat jest wymagany");
        }
        if(email.getText().isBlank()){
            errors.put("textError", "Prosze podać treść wiadmości");
        }
        return errors;
    }
}
