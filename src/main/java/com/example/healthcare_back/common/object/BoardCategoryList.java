package com.example.healthcare_back.common.object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.board.BoardEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class BoardCategoryList {
    private String boardCategory;
    private Integer boardNumber;
    private String boardTitle;
    private String userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime boardUploadDate;
    private Integer boardViewCount;

    public BoardCategoryList(BoardEntity resultSet) {
        this.boardCategory = resultSet.getBoardCategory();
        this.boardNumber = resultSet.getBoardNumber();
        this.boardTitle = resultSet.getBoardTitle();
        this.userId = resultSet.getUserId();
        this.boardUploadDate = resultSet.getBoardUploadDate();
        this.boardViewCount = resultSet.getBoardViewCount();
    }

    public static List<BoardCategoryList> getCategoryList(List<BoardEntity> resultSets) {
        List<BoardCategoryList> boardCategory = new ArrayList<>();
        for (BoardEntity resultSet : resultSets) {
            BoardCategoryList boardCategoryList = new BoardCategoryList(resultSet);
            boardCategory.add(boardCategoryList);
        }
        return boardCategory;
    }
}
