package com.example.healthcare_back.service.implement;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.healthcare_back.service.FileService;

import java.nio.file.Paths;

@Service
public class FileServiceImplement implements FileService {

    @Value("${file.path}")
    private String filePath;

    @Value("${file.url}")
    private String fileUrl;

    @Override
    public String upload(MultipartFile file) {
        // 빈 파일인지 확인
        if (file.isEmpty()) {
            return null; // 또는 적절한 예외 처리
        }

        // 원본 파일명 및 확장자 구하기
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName != null ? originalFileName.substring(originalFileName.lastIndexOf(".")) : "";

        // UUID 형식의 임의의 파일명 생성
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + extension;

        // 파일이 저장될 경로
        String savePath = Paths.get(filePath, saveFileName).toString();

        // 파일 저장
        try {
            file.transferTo(new File(savePath));
        } catch (Exception e) {
            e.printStackTrace(); // 로깅 추가 가능
            return null; // 또는 적절한 예외 처리
        }

        // 파일을 불러올 수 있는 경로 반환
        return fileUrl + saveFileName;
    }

    @Override
    public Resource getFile(String fileName) {
        try {
            Resource resource = new UrlResource("file:" + Paths.get(filePath, fileName).toString());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                // 파일이 존재하지 않거나 읽을 수 없는 경우 처리
                return null; // 또는 적절한 예외 처리
            }
        } catch (Exception e) {
            e.printStackTrace(); // 로깅 추가 가능
            return null; // 또는 적절한 예외 처리
        }
    }
}
