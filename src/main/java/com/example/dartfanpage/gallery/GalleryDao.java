package com.example.dartfanpage.gallery;

import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GalleryDao {

    private static GalleryDao INSTANCE;
    private final String pathGallery = getPathToGalleryDirectory();


    public static GalleryDao getInstance() {
        if(INSTANCE == null){
            synchronized (GalleryDao.class){
                if(INSTANCE == null){
                    INSTANCE = new GalleryDao();
                }
            }
        }
        return INSTANCE;
    }


    public void saveFile(MultipartFile imageFile) throws Exception {
        Random ran = new Random();
        String folder = pathGallery;
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(folder + ran.nextLong() + ".jpg");
        Files.write(path, bytes);
    }

    private String getPathToGalleryDirectory(){
        String path = convertPath(FileSystems.getDefault().getPath("src/main/resources/static/gallery").toAbsolutePath().toString()) ;
        return path;
    }

    private String convertPath(String path){
        String convertString = path.replace("\\", "/");
        return  convertString + "/";
    }

    public List<String> getGallery(){
        File file = new File(pathGallery);
        return Arrays.stream(file.list()).collect(Collectors.toList());
    }



}
