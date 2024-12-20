package com.example.healthcare_back.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.common.object.CommentList;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.board.CommentEntity;

import lombok.Getter;

// 댓글 목록 불러오기 Response DTO

@Getter
public class GetCommentListResponseDto extends ResponseDto {
    
    private final List<CommentList> commentList;

    private GetCommentListResponseDto(List<CommentEntity> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.commentList = CommentList.getList(resultSets);
    }

    public static ResponseEntity<GetCommentListResponseDto> success(List<CommentEntity> resultSets) {
        GetCommentListResponseDto responseBody = new GetCommentListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
