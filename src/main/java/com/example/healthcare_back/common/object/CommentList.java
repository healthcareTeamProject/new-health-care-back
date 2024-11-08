// package com.example.healthcare_back.common.object;

// import java.util.ArrayList;
// import java.util.List;

// import com.example.healthcare_back.repository.resultSet.CommentListResultSet;

// import lombok.Getter;

// // 댓글 리스트를 나타내는 클래스

// @Getter
// public class CommentList {
//     private final Integer commentNumber;
//     private final String commentContents;
//     private final String nickname;
//     private final String commentDate;
//     private final Integer commentLikeCount;

//     public CommentList (CommentListResultSet resultSet) {
//         this.commentNumber = resultSet.getCommentNumber();
//         this.commentContents = resultSet.getCommentContents();
//         this.nickname = resultSet.getNickname();
//         this.commentDate = resultSet.getCommentDate();
//         this.commentLikeCount = resultSet.getCommentLikeCount();
//     }

//     public static List<CommentList> getList(List<CommentListResultSet> resultSets) {
//         List<CommentList> comment = new ArrayList<>();
//         for (CommentListResultSet resultSet: resultSets) {
//             CommentList commentList = new CommentList(resultSet);
//             comment.add(commentList);
//         }
//         return comment;
//     }
// }
