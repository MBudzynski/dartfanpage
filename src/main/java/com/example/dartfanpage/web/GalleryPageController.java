package com.example.dartfanpage.web;

import com.example.dartfanpage.gallery.GalleryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
public class GalleryPageController {

    private GalleryService galleryService;

    @GetMapping("/gallery")
    public String displayMainPage(Model model){
        model.addAttribute("activePage", "gallery");
        model.addAttribute("galleryList", galleryService.getGallery());
        return "gallery.html";
    }

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile){
        try {
            galleryService.saveImage(imageFile);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saving photo " + e);
        }
        return "redirect:/gallery";
    }

}
