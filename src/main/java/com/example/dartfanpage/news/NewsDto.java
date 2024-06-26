package com.example.dartfanpage.news;


import com.example.dartfanpage.news.comment.CommentDto;
import com.example.dartfanpage.news.picture.PictureDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
public class  NewsDto {

    private Long id;
    private String author;
    private LocalDate publicationDate;
    private String mainPicture;
    private String title;
    private String headline;
    private String text;
    private List<PictureDto> pictures;
    private List<CommentDto> comments;

    @Builder(toBuilder = true)
    public NewsDto(Long id, String author, LocalDate publicationDate, String mainPicture,
                   String title, String headline, String text, List<PictureDto> pictures, List<CommentDto> comments) {
        this.id = id;
        this.author = author;
        this.publicationDate = publicationDate;
        this.mainPicture = mainPicture;
        this.title = title;
        this.headline = headline;
        this.text = text;
        this.pictures = pictures;
        this.comments = comments;
    }

    public NewsDto() {
    }

    public News fromDto(){
            return News.builder()
                    .id(id)
                    .author(author)
                    .publicationDate(publicationDate)
                    .mainPicture(mainPicture)
                    .title(title)
                    .headline(headline)
                    .text(text)
                    .pictures(PictureDto.fromDtoList(pictures))
                    .build();

    }

    public static NewsDto toDto(News news){
        return NewsDto.builder()
                .id(news.getId())
                .author(news.getAuthor())
                .publicationDate(news.getPublicationDate())
                .mainPicture(news.getMainPicture())
                .title(news.getTitle())
                .headline(news.getHeadline())
                .text(news.getText())
                .pictures(PictureDto.toDto(news.getPictures()))
                .comments(CommentDto.toDto(news.getComments()))
                .build();
    }

}
