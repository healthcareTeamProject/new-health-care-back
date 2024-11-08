// package com.example.healthcare_back.common.object;

// import java.util.ArrayList;
// import java.util.List;

// import com.example.healthcare_back.repository.resultSet.BoardListResultSet;

// import lombok.Getter;

// @Getter
// public class BoardList {
//     private final Integer boardNumber;
//     private final String boardTitle;
//     private final String nickname;
//     private final String boardUploadDate;
//     private final Integer boardViewCount;

//     public BoardList (BoardListResultSet resultSet) {
//         this.boardNumber = resultSet.getBoardNumber();
//         this.boardTitle = resultSet.getBoardTitle();
//         this.nickname = resultSet.getNickname();
//         this.boardUploadDate = resultSet.getBoardUploadDate();
//         this.boardViewCount = resultSet.getBoardViewCount();
//     }

//     public static List<BoardList> getList(List<BoardListResultSet> resultSets) {
//         List<BoardList> boards = new ArrayList<>();
//         for (BoardListResultSet resultSet: resultSets) {
//             BoardList boardList = new BoardList(resultSet);
//             boards.add(boardList);
//         }
//         return boards;
//     }
// }