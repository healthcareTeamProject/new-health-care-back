package com.example.healthcare_back.common.object;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.repository.resultSet.CommentListResultSet;
=======
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.board.CommentEntity;
>>>>>>> 59ec9a791fce95275047e79dddde2077520ea816

import lombok.Getter;

// 댓글 리스트를 나타내는 클래스

@Getter
public class CommentList {
<<<<<<< HEAD
    private final Integer commentNumber;
    private final String commentContents;
    private final String nickname;
    private final String commentDate;
    private final Integer commentLikeCount;

    public CommentList (CommentListResultSet resultSet) {
        this.commentNumber = resultSet.getCommentNumber();
        this.commentContents = resultSet.getCommentContents();
        this.nickname = resultSet.getNickname();
        this.commentDate = resultSet.getCommentDate();
        this.commentLikeCount = resultSet.getCommentLikeCount();
    }

    public static List<CommentList> getList(List<CommentListResultSet> resultSets) {
        List<CommentList> comment = new ArrayList<>();
        for (CommentListResultSet resultSet: resultSets) {
            CommentList commentList = new CommentList(resultSet);
            comment.add(commentList);
        }
        return comment;
=======
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
>>>>>>> 59ec9a791fce95275047e79dddde2077520ea816
    }
}
