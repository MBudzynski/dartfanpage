package com.example.dartfanpage.downloadFiles;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FileToDownloadDto {

    private Long id;
    private String fileName;
    private String description;

    @Builder(toBuilder = true)
    public FileToDownloadDto(Long id, String fileName, String description) {
        this.id = id;
        this.fileName = fileName;
        this.description = description;
    }

    public FileToDownload fromDto(){
        return FileToDownload.builder()
                .id(id)
                .fileName(fileName)
                .description(description)
                .build();
    }

    public static FileToDownloadDto toDto(FileToDownload fileToDownload){
        return FileToDownloadDto.builder()
                .id(fileToDownload.getId())
                .fileName(fileToDownload.getFileName())
                .description(fileToDownload.getDescription())
                .build();
    }



}
