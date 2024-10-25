package com.example.healthcare_back.common.object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.BoardEntity;

import lombok.Getter;

@Getter
public class BoardList {
    
    private Integer boardNumber;
    private String boardTitle;
    private String nickname;
    private LocalDateTime boardUploadDate;
    private Integer boardViewCount;


    public BoardList (BoardEntity boardEntity) {

        this.boardNumber = boardEntity.getBoardNumber();
        this.boardTitle = boardEntity.getBoardTitle();
        this.nickname = boardEntity.getNickname();
        this.boardUploadDate = LocalDateTime.now();
        this.boardViewCount = boardEntity.getBoardViewCount();

    }

    public static List<BoardList> getList(List<BoardEntity> boardEntities) {

        List<BoardList> boards = new ArrayList<>();
        for (BoardEntity boardEntity: boardEntities) {
            BoardList boardList = new BoardList(boardEntity);
            boards.add(boardList);
        }
        return boards;

    }
}
