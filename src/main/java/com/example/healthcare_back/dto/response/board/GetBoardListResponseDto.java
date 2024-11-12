package com.example.healthcare_back.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;

import lombok.Getter;

// 게시판 목록 불러오기 Response DTO

@Getter
public class GetBoardListResponseDto extends ResponseDto {
    
    private List<GetBoardResponseDto> boardList;

    private GetBoardListResponseDto(List<GetBoardResponseDto> boardResponseList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardList = boardResponseList;
    }

    public static ResponseEntity<GetBoardListResponseDto> success(List<GetBoardResponseDto> boardResponseList) {
        GetBoardListResponseDto responseBody = new GetBoardListResponseDto(boardResponseList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}