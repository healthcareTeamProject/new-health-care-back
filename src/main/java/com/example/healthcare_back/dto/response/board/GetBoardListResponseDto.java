package com.example.healthcare_back.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.common.object.BoardList;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.repository.resultSet.BoardListResultSet;

import lombok.Getter;

// 게시판 목록 불러오기 Response DTO

@Getter
public class GetBoardListResponseDto extends ResponseDto {
    
    private final List<BoardList> boardList;

    private GetBoardListResponseDto(List<BoardListResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardList = BoardList.getList(resultSets);
    }

    public static ResponseEntity<GetBoardListResponseDto> success(List<BoardListResultSet> resultSets) {
        GetBoardListResponseDto responseBody = new GetBoardListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}