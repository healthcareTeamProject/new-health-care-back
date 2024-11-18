package com.example.healthcare_back.dto.request.board;

import java.math.BigDecimal;
import java.util.List;

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
    private String boardCategory;
    private String boardTag;
    private String boardContents;
    private String youtubeVideoLink;
    @Column(precision = 9, scale = 6)
    private BigDecimal Lat;
    @Column(precision = 9, scale = 6)
    private BigDecimal Lng;

    private List<String> boardFileContentsList;

    public List<String> getBoardFileContentsList() {
        return boardFileContentsList;
    }

    public void setBoardFileContentsList(List<String> boardFileContentsList) {
        this.boardFileContentsList = boardFileContentsList;
    }
}

