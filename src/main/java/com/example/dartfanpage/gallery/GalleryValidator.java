package com.example.dartfanpage.gallery;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Component
public class GalleryValidator {

    public Map<String, String> isValid(MultipartFile imageFile) {
        Map<String, String> errors = new HashMap<>();

        if (imageFile.isEmpty() || imageFile == null) {
            errors.put("imageFileError", "Brak pliku");
        }
        return errors;
    }
}
