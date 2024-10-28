package com.example.healthcare_back.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

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

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="comment")
@Table(name="comment")
public class CommentEntity {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer commentNumber;
    private Integer boardNumber;
    private String commentContents;
    private String userId;
    private String commentDate;
    private Integer commentLikeCount = 0; // Initialize to 0 to avoid null values

    public CommentEntity(PostCommentRequestDto dto, Integer boardNumber, String userId) {
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String commentDate = simpleDateFormat.format(now);

        this.boardNumber = boardNumber;
        this.userId = userId;
        this.commentContents = dto.getCommentContents();
        this.commentDate = commentDate;
        this.commentLikeCount = 0; // Initialize to 0 for new comments
    }

    public void increaseFavoriteCount() {
        this.commentLikeCount++;
    }

    public void decreaseFavoriteCount() {
        if (this.commentLikeCount > 0) {
            this.commentLikeCount--;
        }
    }

    public void update(PatchCommentRequestDto dto) {
        // 새로운 댓글 내용으로 수정
        if (dto.getCommentContents() != null && !dto.getCommentContents().isEmpty()) {
            this.commentContents = dto.getCommentContents();
        }
        // 수정된 시간으로 업데이트
        this.commentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date.from(Instant.now()));
    }

}