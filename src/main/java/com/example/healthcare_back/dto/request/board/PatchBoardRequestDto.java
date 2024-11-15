package com.example.healthcare_back.dto.request.board;

import java.math.BigDecimal;

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
    private String boardFileContents;
    private BigDecimal mapLat;
    private BigDecimal mapLng;
}
