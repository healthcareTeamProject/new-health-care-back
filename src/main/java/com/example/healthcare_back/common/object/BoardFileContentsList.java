package com.example.healthcare_back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.board.BoardFileContentsEntity;

import lombok.Getter;

@Getter
public class BoardFileContentsList {
    private final Integer boardFileNumber;
    private final List<String> boardFileContents;

    public BoardFileContentsList(BoardFileContentsEntity boardFileContentsEntity) {
        this.boardFileNumber = boardFileContentsEntity.getBoardFileNumber();
        this.boardFileContents = boardFileContentsEntity.getBoardFileContents();
    }

    public static List<BoardFileContentsList> getList(List<BoardFileContentsEntity> resultSets) {
        List<BoardFileContentsList> boardFileContents = new ArrayList<>();
        for (BoardFileContentsEntity resultSet: resultSets) {
            BoardFileContentsList boardFileContentsList = new BoardFileContentsList(resultSet);
            boardFileContents.add(boardFileContentsList);
        }
        return boardFileContents;
    }
}