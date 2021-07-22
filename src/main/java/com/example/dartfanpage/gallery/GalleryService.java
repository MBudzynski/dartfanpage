package com.example.dartfanpage.gallery;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


@Service
public class GalleryService {

    private final GalleryDao galleryDao = GalleryDao.getInstance();

    public void saveImage(MultipartFile imageFile) throws Exception {
        galleryDao.saveFile(imageFile);
    }

    public List<String> getGallery(){
        return galleryDao.getGallery();
    }

}
