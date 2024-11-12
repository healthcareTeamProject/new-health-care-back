package com.example.healthcare_back.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.common.object.BoardList;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
<<<<<<< HEAD
import com.example.healthcare_back.repository.resultSet.BoardListResultSet;
=======
import com.example.healthcare_back.entity.board.BoardEntity;
>>>>>>> 59ec9a791fce95275047e79dddde2077520ea816

import lombok.Getter;

// 게시판 목록 불러오기 Response DTO

@Getter
public class GetBoardListResponseDto extends ResponseDto {
    
<<<<<<< HEAD
    private final List<BoardList> boardList;

    private GetBoardListResponseDto(List<BoardListResultSet> resultSets) {
=======
    private List<BoardList> boardList;

    private GetBoardListResponseDto(List<BoardEntity> resultSets) {
>>>>>>> 59ec9a791fce95275047e79dddde2077520ea816
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardList = BoardList.getList(resultSets);
    }

<<<<<<< HEAD
    public static ResponseEntity<GetBoardListResponseDto> success(List<BoardListResultSet> resultSets) {
=======
    public static ResponseEntity<GetBoardListResponseDto> success(List<BoardEntity> resultSets) {
>>>>>>> 59ec9a791fce95275047e79dddde2077520ea816
        GetBoardListResponseDto responseBody = new GetBoardListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}