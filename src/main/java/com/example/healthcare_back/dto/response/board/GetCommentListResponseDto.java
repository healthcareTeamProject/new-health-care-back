package com.example.healthcare_back.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.common.object.CommentList;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
<<<<<<< HEAD
import com.example.healthcare_back.repository.resultSet.CommentListResultSet;
=======
import com.example.healthcare_back.entity.board.CommentEntity;
>>>>>>> 59ec9a791fce95275047e79dddde2077520ea816

import lombok.Getter;

// 댓글 목록 불러오기 Response DTO

@Getter
public class GetCommentListResponseDto extends ResponseDto {
    
<<<<<<< HEAD
    private final List<CommentList> commentList;

    private GetCommentListResponseDto(List<CommentListResultSet> resultSets) {
=======
    private List<CommentList> commentList;

    private GetCommentListResponseDto(List<CommentEntity> resultSets) {
>>>>>>> 59ec9a791fce95275047e79dddde2077520ea816
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.commentList = CommentList.getList(resultSets);
    }

<<<<<<< HEAD
    public static ResponseEntity<GetCommentListResponseDto> success(List<CommentListResultSet> resultSets) {
        GetCommentListResponseDto responseBody = new GetCommentListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

=======
    public static ResponseEntity<GetCommentListResponseDto> success(List<CommentEntity> resultSets) {
        GetCommentListResponseDto responseBody = new GetCommentListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
>>>>>>> 59ec9a791fce95275047e79dddde2077520ea816
}
