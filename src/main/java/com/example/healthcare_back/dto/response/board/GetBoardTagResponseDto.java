package com.example.healthcare_back.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetBoardTagResponseDto extends ResponseDto {

    private final List<GetBoardResponseDto> boardTagList;

    private GetBoardTagResponseDto(List<GetBoardResponseDto> boardResponseList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardTagList = boardResponseList;
    }

    public static ResponseEntity<GetBoardTagResponseDto> success(List<GetBoardResponseDto> boardResponseList) {
        GetBoardTagResponseDto responseBody = new GetBoardTagResponseDto(boardResponseList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
