package com.example.healthcare_back.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.common.object.BoardTagList;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.board.BoardEntity;

import lombok.Getter;

@Getter
public class GetBoardTagResponseDto extends ResponseDto {

    private List<BoardTagList> boardTagList;

    private GetBoardTagResponseDto(List<BoardEntity> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardTagList = BoardTagList.getTagList(resultSets);
    }

    public static ResponseEntity<GetBoardTagResponseDto> success(List<BoardEntity> resultSets) {
        GetBoardTagResponseDto responseBody = new GetBoardTagResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
