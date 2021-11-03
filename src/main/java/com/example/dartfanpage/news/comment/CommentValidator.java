package com.example.dartfanpage.news.comment;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommentValidator {

    public Map<String, String> isValid(String text) {
        Map<String, String> errors = new HashMap<>();

        if( text == null || text.isBlank() || text.isEmpty()) {
            errors.put("commentTextError", "Text of comment is required!");
        }

        return errors;
    }
}
