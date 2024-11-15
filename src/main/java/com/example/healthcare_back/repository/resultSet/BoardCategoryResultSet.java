package com.example.healthcare_back.repository.resultSet;

import java.time.LocalDateTime;

public interface BoardCategoryResultSet {
    String getBoardCategory();
    Integer getBoardNumber();
    String getBoardTitle();
    String getUserId();
    LocalDateTime getBoardUploadDate();
    Integer getBoardViewCount();
}
