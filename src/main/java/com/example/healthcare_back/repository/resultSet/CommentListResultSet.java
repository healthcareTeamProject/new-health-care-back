package com.example.healthcare_back.repository.resultSet;

public interface CommentListResultSet {
    
    Integer getCommentNumber();
    String getCommentContents();
    String getNickname();
    String getCommentDate();
    Integer getCommentLikeCount();

}
