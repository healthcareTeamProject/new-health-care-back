package com.example.healthcare_back.common.object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.board.BoardEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class BoardTagList {
    private final String boardTag;
    private final Integer boardNumber;
    private final String boardTitle;
    private final String userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime boardUploadDate;
    private final Integer boardViewCount;

    public BoardTagList(BoardEntity resultSet) {
        this.boardTag = resultSet.getBoardTag();
        this.boardNumber = resultSet.getBoardNumber();
        this.boardTitle = resultSet.getBoardTitle();
        this.userId = resultSet.getUserId();
        this.boardUploadDate = resultSet.getBoardUploadDate();
        this.boardViewCount = resultSet.getBoardViewCount();
    }

    public static List<BoardTagList> getTagList(List<BoardEntity> resultSets) {
        List<BoardTagList> boardTag = new ArrayList<>();
        for (BoardEntity resultSet : resultSets) {
            BoardTagList boardTagList = new BoardTagList(resultSet);
         boardTag.add(boardTagList);
        }
        return boardTag;
    }
}
