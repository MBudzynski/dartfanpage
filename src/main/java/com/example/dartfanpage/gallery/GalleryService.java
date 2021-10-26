package com.example.dartfanpage.gallery;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Random;


@Service
public class GalleryService {

    private final GalleryDao galleryDao = GalleryDao.getInstance();
    private Random ran = new Random();

    public void saveImage(MultipartFile imageFile) {
        try {
            galleryDao.saveFile(imageFile, "gallery", prepareImageFileName(ran));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getGallery(){
        return galleryDao.getGallery();
    }

    private String prepareImageFileName(Random ran){
        return ran.nextLong() + ".jpg";
    }

}
