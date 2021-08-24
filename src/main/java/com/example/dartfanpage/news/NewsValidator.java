package com.example.dartfanpage.news;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NewsValidator {

    public Map<String, String> isValid(NewsDto newsDto) {
        Map<String, String> errors = new HashMap<>();

        if(newsDto.getAuthor() == null || newsDto.getAuthor().isBlank()) {
            errors.put("authorError", "Author of article is required");
        }
        if(newsDto.getTitle() == null || newsDto.getTitle().isBlank()) {
            errors.put("titleError", "Title of article is required");
        }
        if(newsDto.getHeadline() == null || newsDto.getHeadline().isBlank()) {
            errors.put("headlineError", "Headline of article is required");
        }
        if(newsDto.getText() == null || newsDto.getText().isBlank()) {
            errors.put("textError", "Text of article is required");
        }
        return errors;
    }

}
