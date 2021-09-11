package com.example.dartfanpage.news;

import com.example.dartfanpage.news.comment.Comment;
import com.example.dartfanpage.news.comment.CommentDto;
import com.example.dartfanpage.news.comment.CommentRepository;
import com.example.dartfanpage.news.picture.PictureDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NewsService {

    private final ImageDao imageDao = ImageDao.getInstance();

    private final NewsRepository newsRepository;
    private final CommentRepository commentRepository;

    public List<NewsDto> getAllNews() {
        return newsRepository.findAll().stream().map(news -> NewsDto.toDto(news)).collect(Collectors.toList());
    }

    public String saveNews(NewsDto newsDto ,MultipartFile mainPicture, MultipartFile[] pictures) {

        newsDto.setPictures(multipartFileConverter(pictures));

        saveImageOnDirectory(mainPicture);
        if(pictures.length > 0){
            Arrays.stream(pictures).forEach(picture-> saveImageOnDirectory(picture));
        }

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

    public void addComment(CommentDto commentDto) {
        Comment comment = commentDto.fromDto();
        commentRepository.save(comment);
    }
}
