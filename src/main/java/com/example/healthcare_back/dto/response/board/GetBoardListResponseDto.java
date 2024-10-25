package com.example.healthcare_back.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.common.object.BoardList;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.BoardEntity;

import lombok.Getter;

@Getter
public class GetBoardListResponseDto extends ResponseDto {
    
    private List<BoardList> boardLists;

    public GetBoardListResponseDto(List<BoardEntity> boardEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardLists = BoardList.getList(boardEntities);
    }

    public static ResponseEntity<GetBoardListResponseDto> success(List<BoardEntity> boardEntities) {
        GetBoardListResponseDto responseBody = new GetBoardListResponseDto(boardEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
