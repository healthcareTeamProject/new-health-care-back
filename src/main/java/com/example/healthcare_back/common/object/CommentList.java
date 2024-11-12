package com.example.healthcare_back.common.object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.board.CommentEntity;

import lombok.Getter;

// 댓글 리스트를 나타내는 클래스

@Getter
public class CommentList {
    private Integer commentNumber;
    private String commentContents;
    private String userId;
    private LocalDateTime commentDate;
    private Integer commentLikeCount;

    public CommentList(CommentEntity resultSet) {
        this.commentNumber = resultSet.getCommentNumber();
        this.commentContents = resultSet.getCommentContents();
        this.userId = resultSet.getUserId();
        this.commentDate = LocalDateTime.now();
        this.commentLikeCount = resultSet.getCommentLikeCount();
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
