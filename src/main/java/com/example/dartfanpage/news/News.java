package com.example.dartfanpage.news;

import com.example.dartfanpage.news.picture.Picture;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String author;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;
    @Lob
    private String mainPicture;
    private String title;
    private String headline;
    @Lob
    private String text;
    @OneToMany(mappedBy = "news",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Picture> pictures;

    @Builder(toBuilder = true)
    public News(Long id,
                String author,
                LocalDate publicationDate,
                String mainPicture,
                String title,
                String headline,
                String text,
                List<Picture> pictures) {
        this.id = id;
        this.author = author;
        this.publicationDate = publicationDate;
        this.mainPicture = mainPicture;
        this.title = title;
        this.headline = headline;
        this.text = text;
        this.pictures = attachParentToPicture(pictures);
    }

    private List<Picture> attachParentToPicture(List<Picture> pictures) {
        return pictures.stream()
                .map(equipment -> equipment.toBuilder().news(this).build())
                .collect(Collectors.toUnmodifiableList());
    }
}
