package com.example.healthcare_back.dto.response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class IncreaseViewCountResponseDto extends ResponseDto {

    private Integer boardNumber; // 조회수를 증가시킨 게시물 번호

    public IncreaseViewCountResponseDto(Integer boardNumber) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardNumber = boardNumber;
    }

    public static ResponseEntity<IncreaseViewCountResponseDto> success(Integer boardNumber) {
        IncreaseViewCountResponseDto responseBody = new IncreaseViewCountResponseDto(boardNumber);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistBoard() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_BOARD, ResponseMessage.NO_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}