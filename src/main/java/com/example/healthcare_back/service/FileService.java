package com.example.healthcare_back.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    
    // 파일 업로드
    String upload(MultipartFile file);

    // 파일 불러오기
    Resource getFile(String fileName);
    
}