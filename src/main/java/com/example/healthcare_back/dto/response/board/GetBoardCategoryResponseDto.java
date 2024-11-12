package com.example.healthcare_back.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.common.object.BoardCategoryList;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.board.BoardEntity;

import lombok.Getter;

@Getter
public class GetBoardCategoryResponseDto extends ResponseDto {
    
    private List<BoardCategoryList> boardCategoryList;
    
        private GetBoardCategoryResponseDto(List<BoardEntity> resultSets) {
            super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
            this.boardCategoryList = BoardCategoryList.getCategoryList(resultSets);
    }

    public static ResponseEntity<GetBoardCategoryResponseDto> success(List<BoardEntity> resultSets) {
        GetBoardCategoryResponseDto responseBody = new GetBoardCategoryResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
