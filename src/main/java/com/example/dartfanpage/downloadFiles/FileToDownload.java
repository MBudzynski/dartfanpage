package com.example.dartfanpage.downloadFiles;

import com.example.dartfanpage.BaseEntity;
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
public class FileToDownload extends BaseEntity {

    private String fileName;
    private String description;

    @Builder(toBuilder = true)
    public FileToDownload(Long id, String fileName, String description) {
        super(id);
        this.fileName = fileName;
        this.description = description;
    }
}
