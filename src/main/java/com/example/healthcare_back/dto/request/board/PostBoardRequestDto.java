package com.example.healthcare_back.dto.request.board;

import java.math.BigDecimal;
import java.util.List;

import com.example.healthcare_back.entity.board.StringListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
    private BigDecimal mapLat;
    @Column(precision = 9, scale = 6)
    private BigDecimal mapLng;
    @Convert(converter = StringListConverter.class)
    private List<String> boardFileContents;
}
