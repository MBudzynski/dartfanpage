package com.example.dartfanpage.news;

import com.example.dartfanpage.gallery.SaveFileImplement;
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

public class ImageDao extends SaveFileImplement {

    private static ImageDao INSTANCE;


    public static ImageDao getInstance() {
        if(INSTANCE == null){
            synchronized (ImageDao.class){
                if(INSTANCE == null){
                    INSTANCE = new ImageDao();
                }
            }
        }
        return INSTANCE;
    }


}
