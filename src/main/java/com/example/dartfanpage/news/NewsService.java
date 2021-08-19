package com.example.dartfanpage.news;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NewsService {

    private final ImageDao imageDao = ImageDao.getInstance();

    private final NewsRepository newsRepository;

    public List<NewsDto> getAllNews() {
        return newsRepository.findAll().stream().map(news -> news.toDto()).collect(Collectors.toList());
    }


    public String saveNews(String author, MultipartFile articlePicture, String title, String headline, String text) {

        NewsDto dto = new NewsDto(author, LocalDate.now(), articlePicture.getOriginalFilename(), title, headline, text);
        try {
            imageDao.saveFile(articlePicture, "images" ,articlePicture.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(dto.getPicture() != null) {
            newsRepository.save(News.fromDto(dto));
            return "Article saved";
        } else {
            return "Saving picture ERROR";
        }
    }


}
