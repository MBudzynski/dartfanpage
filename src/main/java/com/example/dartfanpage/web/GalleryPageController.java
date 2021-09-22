package com.example.dartfanpage.web;

import com.example.dartfanpage.gallery.GalleryService;
import com.example.dartfanpage.gallery.GalleryValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@AllArgsConstructor
public class GalleryPageController {

    private GalleryService galleryService;

    private GalleryValidator galleryValidator;

    @GetMapping("/gallery")
    public String displayMainPage(Model model){
        model.addAttribute("activePage", "gallery");
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("galleryList", galleryService.getGallery());
        return "gallery.html";
    }

    @PostMapping("/uploadImage")
    @PreAuthorize("hasRole('ADMIN')")
    public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile, Model model){
        Map<String, String> errorMap = galleryValidator.isValid(imageFile);
        if (errorMap.isEmpty()) {
                galleryService.saveImage(imageFile);
            return "redirect:/gallery";
        }
        model.addAttribute("activePage", "gallery");
        model.addAttribute("galleryList", galleryService.getGallery());
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute(errorMap);
        return "gallery";
    }

}
