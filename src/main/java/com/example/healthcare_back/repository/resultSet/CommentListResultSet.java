package com.example.healthcare_back.repository.resultSet;

import java.time.LocalDateTime;

public interface CommentListResultSet {
    Integer getCommentNumber();
    String getCommentContents();
    String getUserId();
    LocalDateTime getCommentDate();
    Integer getCommentLikeCount();
}