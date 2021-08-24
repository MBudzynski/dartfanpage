package com.example.dartfanpage.news.comment;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommentValidator {

    public Map<String, String> isValid(String text) {
        Map<String, String> errors = new HashMap<>();

        if(text.isBlank() || text.isEmpty()) {
            errors.put("commentTextError", "Treść komentarza jest wymagana");
        }
        return errors;
    }
}
