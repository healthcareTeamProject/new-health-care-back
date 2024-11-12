package com.example.healthcare_back.common.object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.board.BoardEntity;

import lombok.Getter;

@Getter
public class BoardCategoryList {
    private String boardCategory;
    private Integer boardNumber;
    private String boardTitle;
    private String userId;
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
        List<BoardCategoryList> boards = new ArrayList<>();
        for (BoardEntity resultSet : resultSets) {
            BoardCategoryList boardCategoryList = new BoardCategoryList(resultSet);
            boards.add(boardCategoryList);
        }
        return boards;
    }
}
