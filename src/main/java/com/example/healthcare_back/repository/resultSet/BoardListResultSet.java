package com.example.healthcare_back.repository.resultSet;

public interface BoardListResultSet {
    Integer getBoardNumber();
    String getBoardTitle();
    String getNickname();
    String getBoardUploadDate();
    Integer getBoardViewCount();
}
