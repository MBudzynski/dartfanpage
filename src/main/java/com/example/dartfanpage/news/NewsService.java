package com.example.dartfanpage.news;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class NewsService {

    private NewsRepository newsRepository;

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }


    public String saveNews(String author, MultipartFile articlePicture, String title, String headline, String text) {

        NewsDto dto = new NewsDto(author, LocalDate.now(), imageParse(articlePicture), title, headline, text);
        if(dto.getPicture() != null) {
            newsRepository.save(News.fromDto(dto));
            return "Article saved";
        } else {
            return "Saving picture ERROR";
        }
    }

    private byte[] imageParse(MultipartFile multipartFile){
        try {
            return multipartFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
