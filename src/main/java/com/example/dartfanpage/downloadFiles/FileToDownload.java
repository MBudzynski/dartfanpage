package com.example.dartfanpage.downloadFiles;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class FileToDownload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String description;

    @Builder(toBuilder = true)
    public FileToDownload(Long id, String fileName, String description) {
        this.id = id;
        this.fileName = fileName;
        this.description = description;
    }
}
