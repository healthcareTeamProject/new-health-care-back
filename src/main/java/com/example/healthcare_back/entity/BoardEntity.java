package com.example.healthcare_back.entity;

import com.example.healthcare_back.dto.request.board.PatchBoardRequestDto;
import com.example.healthcare_back.dto.request.board.PostBoardRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 게시판 엔터티

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board")
@Table(name="board")
public class BoardEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer boardNumber;
    private String boardTitle;
    private String nickname;
    private LocalDateTime boardUploadDate; 
    private String boardContents;
    private String youtubeVideoLink;
    private String boardFileContents;
    private Integer boardViewCount;
    private Integer boardLikeCount;
    private Integer commentCount;
    

    public BoardEntity(PostBoardRequestDto dto, String userId) {
        this.boardTitle = dto.getBoardTitle();
        this.nickname = userId;
        this.boardUploadDate = LocalDateTime.now(); // 현재 날짜와 시간
        this.boardContents = dto.getBoardContents();
        this.youtubeVideoLink = dto.getYoutubeVideoLink();
        this.boardFileContents = dto.getBoardFileContents();
        this.boardViewCount = 0;
        this.boardLikeCount = 0;
        this.commentCount = 0;
    }

    public void increaseViewCount() {
        this.boardViewCount++;
    }

    public void increaseCommentCount() {
        this.commentCount++;
    }

    public void increaseLikeCount() {
        this.boardLikeCount++;
    }

    public void decreaseLikeCount() {
        this.boardLikeCount--;
    }

    public void update(PatchBoardRequestDto dto) {
        // 수정된 내용으로 게시물 내용 변경
        if (dto.getBoardTitle() != null && !dto.getBoardTitle().isEmpty()) {
            this.boardTitle = dto.getBoardTitle();
        }
        if (dto.getBoardContents() != null && !dto.getBoardContents().isEmpty()) {
            this.boardContents = dto.getBoardContents();
        }
        if (dto.getYoutubeVideoLink() != null && !dto.getYoutubeVideoLink().isEmpty()) {
            this.youtubeVideoLink = dto.getYoutubeVideoLink();
        }
        if (dto.getBoardFileContents() != null && !dto.getBoardFileContents().isEmpty()) {
            this.boardFileContents = dto.getBoardFileContents();
        }
    }
}
