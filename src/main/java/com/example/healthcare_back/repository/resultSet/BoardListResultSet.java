package com.example.healthcare_back.repository.resultSet;

import java.time.LocalDateTime;

public interface BoardListResultSet {
    Integer getBoardNumber();

    String getBoardTitle();

    String getUserId();

    LocalDateTime getBoardUploadDate();

    Integer getBoardViewCount();
}