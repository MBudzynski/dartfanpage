package com.example.dartfanpage.gallery;



import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GalleryDao extends SaveFileImplement {

    private static GalleryDao INSTANCE;
    private final String pathGalleryDirectory = getPathToDirectory("gallery");


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

    public List<String> getGallery(){
        File file = new File(pathGalleryDirectory);
        return Arrays.stream(file.list()).collect(Collectors.toList());
    }

}
