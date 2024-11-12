package com.example.healthcare_back.dto.request.board;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 게시물 작성 Request Body DTO

@Getter
@Setter
@NoArgsConstructor
public class PostBoardRequestDto {
    
    private String boardTitle;
    
    private String userId;
    
    private String boardCategory;
    
    private String boardTag;
    
    private String boardContents;
    
    private String youtubeVideoLink;
    
    private String boardFileContents;
    @Column(precision = 9, scale = 6)
    private BigDecimal mapLat;
    @Column(precision = 9, scale = 6)
    private BigDecimal mapLng;
}

