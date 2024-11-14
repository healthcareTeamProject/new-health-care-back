package com.example.healthcare_back.common.object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.board.BoardEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class BoardList {
    private Integer boardNumber;
    private String boardTitle;
    private String userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime boardUploadDate;
    private Integer boardViewCount;

    public BoardList(BoardEntity resultSet) {
        this.boardNumber = resultSet.getBoardNumber();
        this.boardTitle = resultSet.getBoardTitle();
        this.userId = resultSet.getUserId();
        this.boardUploadDate = resultSet.getBoardUploadDate();
        this.boardViewCount = resultSet.getBoardViewCount();
    }

    public static List<BoardList> getList(List<BoardEntity> resultSets) {
        List<BoardList> boards = new ArrayList<>();
        for (BoardEntity resultSet : resultSets) {
            BoardList boardList = new BoardList(resultSet);
            boards.add(boardList);
        }
        return boards;
    }
}