package com.example.dartfanpage.downloadFiles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileToDownloadRepository extends JpaRepository<FileToDownload, Long> {
}
