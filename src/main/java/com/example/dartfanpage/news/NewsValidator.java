package com.example.dartfanpage.news;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NewsValidator {

    public Map<String, String> isValid(NewsDto newsDto) {
        Map<String, String> errors = new HashMap<>();

        if(newsDto.getAuthor() == null || newsDto.getAuthor().isBlank()|| !newsDto.getAuthor().matches("[A-złńśóćźżąę -]{3,}")) {
            errors.put("authorError", "Author of article is required");
        }
        if(newsDto.getTitle() == null || newsDto.getTitle().isBlank() || !newsDto.getTitle().matches("[A-złńśćźżóąę0-9 -,;:.]{10,100}")) {
            errors.put("titleError", "Title must have between 10 and 100 chars");
        }
        if(newsDto.getHeadline() == null || newsDto.getHeadline().isBlank() || !newsDto.getHeadline().matches("[A-złńśćźżąóę0-9 -,;:.]{10,300}")) {
            errors.put("headlineError", "Headline must have between 10 and 300 chars");
        }
        if(newsDto.getText() == null || newsDto.getText().isBlank() || !newsDto.getText().matches("[A-złńśćźóżąę0-9 -,;:.]{10,800}")) {
            errors.put("textError", "Text of article must have between 10 and 1000 chars");
        }

        return errors;
    }
}
