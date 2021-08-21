package com.example.dartfanpage.news;

import com.example.dartfanpage.news.picture.PictureDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NewsService {

    private final ImageDao imageDao = ImageDao.getInstance();

    private final NewsRepository newsRepository;

    public List<NewsDto> getAllNews() {
        return newsRepository.findAll().stream().map(news -> NewsDto.toDto(news)).collect(Collectors.toList());
    }

    public String saveNews(String author, MultipartFile mainPicture, String title,
                           String headline, String text, MultipartFile[] pictures) {

        NewsDto newsDto = NewsDto.builder()
                .author(author)
                .title(title)
                .publicationDate(LocalDate.now())
                .mainPicture(mainPicture.getOriginalFilename())
                .headline(headline)
                .text(text)
                .pictures(multipartFileConverter(pictures))
                .build();

        saveImageOnDirectory(mainPicture);
        Arrays.stream(pictures).forEach(picture-> saveImageOnDirectory(picture));

        if (newsDto.getMainPicture() != null) {
            newsRepository.save(newsDto.fromDto());
            return "Article saved";
        } else {
            return "Saving picture ERROR";
        }
    }

    private void saveImageOnDirectory(MultipartFile mainPicture) {
        try {
            imageDao.saveFile(mainPicture, "images", mainPicture.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<NewsDto> findNewsById(Long id) {
        return newsRepository.findById(id).map(news -> NewsDto.toDto(news));
    }

    private List<PictureDto> multipartFileConverter(MultipartFile[] pictures){
      return Arrays.stream(pictures).map(picture -> PictureDto.builder().pictureName(picture.getOriginalFilename()).build()).collect(Collectors.toList());
    }

}
