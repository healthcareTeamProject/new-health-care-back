package com.example.healthcare_back.entity.board;

import java.time.LocalDateTime;

import com.example.healthcare_back.dto.request.board.PatchBoardRequestDto;
import com.example.healthcare_back.dto.request.board.PostBoardRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 게시판 엔터티

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board")
@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardNumber;

    private String boardTitle;

    private String userId;

    private String boardCategory;

    private String boardTag;

    private String boardContents;

    private String youtubeVideoLink;

    private LocalDateTime boardUploadDate;

    private Integer boardViewCount;

    private Integer boardLikeCount;

    public BoardEntity(PostBoardRequestDto dto, String userId) {
        this.boardUploadDate = LocalDateTime.now();
        this.userId = userId;
        this.boardTitle = dto.getBoardTitle();
        this.userId = dto.getUserId();
        this.boardCategory = dto.getBoardCategory();
        this.boardTag = dto.getBoardTag();
        this.boardContents = dto.getBoardContents();
        this.youtubeVideoLink = dto.getYoutubeVideoLink();
        this.boardViewCount = 0;
        this.boardLikeCount = 0;
    }

    public void increaseViewCount() {
        this.boardViewCount++;
    }

    public void increaseLikeCount() {
        this.boardLikeCount++;
    }

    public void decreaseLikeCount() {
        this.boardLikeCount--;
    }

    public void update(PatchBoardRequestDto dto, Integer boardNumber, String userId) {
        this.boardNumber = boardNumber;
        this.userId = userId;
        this.boardTitle = dto.getBoardTitle();
        this.boardCategory = dto.getBoardCategory();
        this.boardTag = dto.getBoardTag();
        this.boardContents = dto.getBoardContents();
        this.youtubeVideoLink = dto.getYoutubeVideoLink();
    }
}