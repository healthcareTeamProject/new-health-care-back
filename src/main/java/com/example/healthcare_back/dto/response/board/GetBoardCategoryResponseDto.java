package com.example.healthcare_back.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetBoardCategoryResponseDto extends ResponseDto {

    private final List<GetBoardResponseDto> boardCategoryList;

    private GetBoardCategoryResponseDto(List<GetBoardResponseDto> boardResponseList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardCategoryList = boardResponseList;
    }

    public static ResponseEntity<GetBoardCategoryResponseDto> success(List<GetBoardResponseDto> boardResponseList) {
        GetBoardCategoryResponseDto responseBody = new GetBoardCategoryResponseDto(boardResponseList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
