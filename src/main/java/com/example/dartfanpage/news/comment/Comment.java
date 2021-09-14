package com.example.dartfanpage.news.comment;

import com.example.dartfanpage.BaseEntity;
import com.example.dartfanpage.news.News;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity {

    private String author;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataTime;
    @Lob
    @NotBlank(message = "Content of the comment is required")
    @NotNull(message = "Text of comment is required")
    private String text;
    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    @Builder(toBuilder = true)
    public Comment(Long id, String author, LocalDateTime dataTime, String text, News news) {
        super(id);
        this.author = author;
        this.dataTime = dataTime;
        this.text = text;
        this.news = news;
    }
}
