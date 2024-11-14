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
import lombok.Setter;

// 게시판 엔터티

@Getter
@Setter
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

    private Integer boardViewCount = 0;
    
    private Integer boardLikeCount = 0;

    public BoardEntity(PostBoardRequestDto dto, String userId) {
        this.boardUploadDate = LocalDateTime.now();
        this.userId = userId;
        this.boardTitle = dto.getBoardTitle();
        this.boardCategory = dto.getBoardCategory();
        this.boardTag = dto.getBoardTag();
        this.boardContents = dto.getBoardContents();
        this.youtubeVideoLink = dto.getYoutubeVideoLink();
    }

    public void increaseViewCount() { this.boardViewCount++; }
    public void increaseLikeCount() { this.boardLikeCount++; }
    public void decreaseLikeCount() { this.boardLikeCount--; }

    public void update(PatchBoardRequestDto dto, Integer boardNumber, String userId) {
        this.boardNumber = boardNumber;
        this.boardTitle = dto.getBoardTitle();
        this.boardCategory = dto.getBoardCategory();
        this.boardTag = dto.getBoardTag();
        this.boardContents = dto.getBoardContents();
        this.youtubeVideoLink = dto.getYoutubeVideoLink();
    }
}