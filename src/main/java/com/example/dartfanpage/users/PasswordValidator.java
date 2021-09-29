package com.example.dartfanpage.users;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PasswordValidator {

    public Map<String, String> isValid(String password) {
        Map<String, String> errors = new HashMap<>();

        if (password == null || !password.matches("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{10,20})")) {
            errors.put("passwordError", "Hasło jest wymagane. Musi zawierać od 10 do 20 znaków, cyfre oraz jedna duża i jedna mała litere.");
        }

        return errors;
    }
}
