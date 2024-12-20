package com.example.healthcare_back.entity.board;

import java.time.LocalDateTime;

import com.example.healthcare_back.dto.request.board.PatchCommentRequestDto;
import com.example.healthcare_back.dto.request.board.PostCommentRequestDto;
import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime commentDate;
    private Integer commentLikeCount = 0;


    public CommentEntity(PostCommentRequestDto dto, Integer boardNumber, String userId) {
        this.commentDate = LocalDateTime.now();
        this.boardNumber = boardNumber;
        this.userId = userId;
        this.commentContents = dto.getCommentContents();
    }

    public void increaseLikeCount() { this.commentLikeCount++; }
    public void decreaseLikeCount() { this.commentLikeCount--; }

    public void update(PatchCommentRequestDto dto, Integer boardNumber, Integer commentNumber, String userId) {
        this.commentDate = LocalDateTime.now();
        this.boardNumber = boardNumber;
        this.commentNumber = commentNumber;
        this.userId = userId;
        this.commentContents = dto.getCommentContents();
    }
}