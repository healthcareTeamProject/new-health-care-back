package com.example.healthcare_back.dto.request.board;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 게시판 수정 Request Body DTO

@Getter
@Setter
@NoArgsConstructor
public class PatchBoardRequestDto {
    private String boardTitle;
    private String boardCategory;
    private String boardTag;
    private String boardContents;
    private String youtubeVideoLink;
    @Column(precision = 9, scale = 6)
    private BigDecimal mapLat;
    @Column(precision = 9, scale = 6)
    private BigDecimal mapLng;
    private String boardFileContents;
}
