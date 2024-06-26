package com.example.dartfanpage.downloadFiles;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FileToDownloadService {

    private final FileToDownloadRepository fileToDownloadRepository;
    private final FileToDownloadDao fileToDownloadDao;

    public void saveFileToDownload(FileToDownloadDto fileToDownloadDto, MultipartFile imageFile) {
        fileToDownloadRepository.save(fileToDownloadDto.fromDto());
        try {
            fileToDownloadDao.saveFile(imageFile,"todownload", imageFile.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<FileToDownloadDto> getFileToDownload() {
        return fileToDownloadRepository.findAll().stream().map(FileToDownloadDto::toDto).collect(Collectors.toList());
    }

    public void removeFile(Long id){
        fileToDownloadRepository.deleteById(id);
    }
}
