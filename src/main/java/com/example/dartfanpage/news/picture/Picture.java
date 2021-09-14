package com.example.dartfanpage.news.picture;

import com.example.dartfanpage.BaseEntity;
import com.example.dartfanpage.news.News;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
public class Picture extends BaseEntity {

    private String pictureName;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    @Builder(toBuilder = true)
    public Picture(Long id, String pictureName, News news) {
        super(id);
        this.pictureName = pictureName;
        this.news = news;
    }


}
