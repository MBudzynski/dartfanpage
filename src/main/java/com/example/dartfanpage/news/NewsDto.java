package com.example.dartfanpage.news;


import com.example.dartfanpage.news.comment.CommentDto;
import com.example.dartfanpage.news.picture.PictureDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder(toBuilder = true)
public class NewsDto {

    private Long id;
    private String author;
    private LocalDate publicationDate;
    private String mainPicture;
    private String title;
    private String headline;
    private String text;
    private List<PictureDto> pictures;
    private List<CommentDto> comments;

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

    public void addCommentToList(CommentDto commentDto){
        this.comments.add(commentDto);
    }
}
