package com.example.dartfanpage.web;

import com.example.dartfanpage.downloadFiles.FileToDownloadDto;
import com.example.dartfanpage.downloadFiles.FileToDownloadService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.Part;

@Controller
@AllArgsConstructor
public class AboutUsController {

    private final FileToDownloadService fileToDownloadService;

    @GetMapping("/aboutUs")
    String displayAboutUsPage(Model model){
        model.addAttribute("files" , fileToDownloadService.getFileToDownload());
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("activePage", "aboutUs");

        return "aboutUs.html";
    }

    @PostMapping("/addFileToDownload")
    @PreAuthorize("hasRole('ADMIN')")
    String addFileToDownload(@RequestParam("fileToDownload") MultipartFile imageFile , @RequestParam String description){

        FileToDownloadDto fileToDownloadDto = FileToDownloadDto.builder()
                .fileName(imageFile.getOriginalFilename())
                .description(description).build();

        fileToDownloadService.saveFileToDownload(fileToDownloadDto, imageFile);

        return "redirect:/aboutUs";
    }
}
