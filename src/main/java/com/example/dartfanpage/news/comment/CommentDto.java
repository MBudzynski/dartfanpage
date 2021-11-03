package com.example.dartfanpage.news.comment;

import com.example.dartfanpage.news.NewsDto;
import com.example.dartfanpage.news.picture.Picture;
import com.example.dartfanpage.news.picture.PictureDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder(toBuilder = true)
public class CommentDto {

    private Long id;
    private String author;
    private LocalDateTime dataTime;
    private String text;
    private NewsDto news;


    public Comment fromDto(){
        return Comment.builder()
                .author(author)
                .dataTime(dataTime)
                .text(text)
                .news(news.fromDto())
                .build();
    }


    public static CommentDto toDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .author(comment.getAuthor())
                .dataTime(comment.getDataTime())
                .text(comment.getText())
                .build();
    }

    public static List<CommentDto> toDto(List<Comment> entities) {
        return entities.stream().map(CommentDto::toDto).collect(Collectors.toList());
    }


}
