package com.example.dartfanpage.news.picture;

import com.example.dartfanpage.news.NewsDto;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Getter
@Setter
public class PictureDto {

    private Long id;
    private String pictureName;
    private NewsDto newsDto;

    @Builder(toBuilder = true)
    public PictureDto(Long id, String pictureName, NewsDto newsDto) {
        this.id = id;
        this.pictureName = pictureName;
        this.newsDto = newsDto;
    }

    public Picture toEntity() {
        return Picture.builder().id(id).pictureName(pictureName).build();
    }

    public static List<Picture> fromDtoList(List<PictureDto> equipments) {
        return Objects.isNull(equipments)
                ? List.of()
                : equipments.stream().map(PictureDto::toEntity).collect(Collectors.toUnmodifiableList());
    }

    public static PictureDto toDto(Picture entity) {
        return PictureDto.builder().id(entity.getId()).pictureName(entity.getPictureName()).build();
    }

    public static List<PictureDto> toDto(List<Picture> entities) {
        return entities.stream().map(PictureDto::toDto).collect(Collectors.toUnmodifiableList());
    }
}
