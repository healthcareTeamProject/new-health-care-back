package com.example.healthcare_back.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

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
    private Integer commentLikeCount;

    public CommentEntity(PostCommentRequestDto dto, Integer boardNumber, String userId) {
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String commentDate = simpleDateFormat.format(now);

        this.boardNumber = boardNumber;
        this.userId = userId;
        this.commentContents = dto.getCommentContents();
        this.commentDate = commentDate;

    }

    public void increaseFavoriteCount() {
        this.commentLikeCount++;
    }

    public void decreaseFavoriteCount() {
        this.commentLikeCount--;
    }

}