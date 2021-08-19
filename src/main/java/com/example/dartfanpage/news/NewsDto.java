package com.example.dartfanpage.news;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NewsDto {

    private Long id;
    private String author;
    private LocalDate publicationDate;
    private String picture;
    private String title;
    private String headline;
    private String text;

    public NewsDto(Long id, String author, LocalDate publicationDate, String picture, String title, String headline, String text) {
        this.id = id;
        this.author = author;
        this.publicationDate = publicationDate;
        this.picture = picture;
        this.title = title;
        this.headline = headline;
        this.text = text;
    }

    public NewsDto(String author, LocalDate publicationDate, String picture, String title, String headline, String text) {
        this.author = author;
        this.publicationDate = publicationDate;
        this.picture = picture;
        this.title = title;
        this.headline = headline;
        this.text = text;
    }
}
