package com.example.dartfanpage.news;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String author;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;
    @Lob
    private byte[] picture;
    private String title;
    private String headline;
    @Lob
    private String text;

    public NewsDto toDto() {
        return new NewsDto(
                this.id,
                this.author,
                this.publicationDate,
                this.picture,
                this.title,
                this.headline,
                this.text);
    }

    public static News fromDto(NewsDto dto){
        News news = new News();
        news.author = dto.getAuthor();
        news.publicationDate = dto.getPublicationDate();
        news.picture = dto.getPicture();
        news.title = dto.getTitle();
        news.headline = dto.getHeadline();
        news.text = dto.getText();
        return news;
    }

}
