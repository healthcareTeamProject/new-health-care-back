package com.example.healthcare_back.dto.response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;

import lombok.Getter;

// 조회수 증가 Response DTO

@Getter
public class IncreaseViewCountResponseDto extends ResponseDto {
    
    public IncreaseViewCountResponseDto(String code, String message, Integer boardNumber) {
        super(code, message);
    }

    public static ResponseEntity<ResponseDto> notExistBoard() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_BOARD, ResponseMessage.NO_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}