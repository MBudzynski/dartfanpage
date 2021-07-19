package com.example.dartfanpage.gallery;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class GalleryService {

    public void saveImage(MultipartFile imageFile) throws Exception {
        String folder = getPathToGalleryDirectory();
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(folder + imageFile.getOriginalFilename());
        Files.write(path, bytes);

    }

    private String getPathToGalleryDirectory(){
        return FileSystems.getDefault().getPath("gallery").toAbsolutePath().toString();
    }
}
