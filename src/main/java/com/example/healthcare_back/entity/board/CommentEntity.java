package com.example.healthcare_back.entity.board;

import java.time.LocalDateTime;

import com.example.healthcare_back.dto.request.board.PatchCommentRequestDto;
import com.example.healthcare_back.dto.request.board.PostCommentRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 댓글 엔터티

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "comment")
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentNumber;

    private String userId;

    private Integer boardNumber;

    private String commentContents;

    private LocalDateTime commentDate;

    private Integer commentLikeCount;

    public CommentEntity(PostCommentRequestDto dto, Integer boardNumber, String userId) {
        this.commentDate = LocalDateTime.now();
        this.boardNumber = boardNumber;
        this.userId = userId;
        this.commentLikeCount = 0;
        this.commentContents = dto.getCommentContents();
    }

    public void increaseFavoriteCount() {
        this.commentLikeCount++;
    }

    public void decreaseFavoriteCount() {
        this.commentLikeCount--;
    }

    public void update(PatchCommentRequestDto dto, Integer boardNumber, Integer commentNumber, String userId) {
        this.commentDate = LocalDateTime.now();
        this.boardNumber = boardNumber;
        this.commentNumber = commentNumber;
        this.userId = userId;
        this.commentContents = dto.getCommentContents();
    }
}