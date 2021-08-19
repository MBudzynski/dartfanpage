package com.example.dartfanpage.gallery;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class SaveFileImplement {



    public void saveFile(MultipartFile imageFile, String directory, String fileName) throws Exception {
        String folder = getPathToDirectory(directory);
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(folder + fileName);
        Files.write(path, bytes);
    }

    protected String getPathToDirectory(String directory) {
        String path = convertPath(FileSystems.getDefault().getPath("src/main/resources/static/"+ directory).toAbsolutePath().toString()) ;
        return path;
    }

    private String convertPath(String path){
        String convertString = path.replace("\\", "/");
        return  convertString + "/";
    }



}
