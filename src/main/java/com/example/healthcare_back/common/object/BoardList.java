package com.example.healthcare_back.common.object;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.repository.resultSet.BoardListResultSet;
=======
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.board.BoardEntity;
>>>>>>> 59ec9a791fce95275047e79dddde2077520ea816

import lombok.Getter;

@Getter
public class BoardList {
<<<<<<< HEAD
    private final Integer boardNumber;
    private final String boardTitle;
    private final String nickname;
    private final String boardUploadDate;
    private final Integer boardViewCount;

    public BoardList (BoardListResultSet resultSet) {
        this.boardNumber = resultSet.getBoardNumber();
        this.boardTitle = resultSet.getBoardTitle();
        this.nickname = resultSet.getNickname();
=======
    private Integer boardNumber;
    private String boardTitle;
    private String userId;
    private LocalDateTime boardUploadDate;
    private Integer boardViewCount;

    public BoardList(BoardEntity resultSet) {
        this.boardNumber = resultSet.getBoardNumber();
        this.boardTitle = resultSet.getBoardTitle();
        this.userId = resultSet.getUserId();
>>>>>>> 59ec9a791fce95275047e79dddde2077520ea816
        this.boardUploadDate = resultSet.getBoardUploadDate();
        this.boardViewCount = resultSet.getBoardViewCount();
    }

<<<<<<< HEAD
    public static List<BoardList> getList(List<BoardListResultSet> resultSets) {
        List<BoardList> boards = new ArrayList<>();
        for (BoardListResultSet resultSet: resultSets) {
=======
    public static List<BoardList> getList(List<BoardEntity> resultSets) {
        List<BoardList> boards = new ArrayList<>();
        for (BoardEntity resultSet : resultSets) {
>>>>>>> 59ec9a791fce95275047e79dddde2077520ea816
            BoardList boardList = new BoardList(resultSet);
            boards.add(boardList);
        }
        return boards;
    }
}