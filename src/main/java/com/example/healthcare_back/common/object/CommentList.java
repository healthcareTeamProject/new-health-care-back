package com.example.healthcare_back.common.object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.board.CommentEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

// 댓글 리스트를 나타내는 클래스

@Getter
public class CommentList {
    private Integer commentNumber;
    private String commentContents;
    private String userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime commentDate;
    private Integer commentLikeCount;

    public CommentList(CommentEntity commentEntity) {
        this.commentNumber = commentEntity.getCommentNumber();
        this.commentContents = commentEntity.getCommentContents();
        this.userId = commentEntity.getUserId();
        this.commentDate = commentEntity.getCommentDate();
        this.commentLikeCount = commentEntity.getCommentLikeCount();
    }

    public static List<CommentList> getList(List<CommentEntity> resultSets) {
        List<CommentList> comments = new ArrayList<>();
        for (CommentEntity resultSet: resultSets) {
            CommentList commentList = new CommentList(resultSet);
            comments.add(commentList);
        }
        return comments;
    }
}
