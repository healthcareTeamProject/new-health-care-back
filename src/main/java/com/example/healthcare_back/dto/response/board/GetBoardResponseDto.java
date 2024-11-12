package com.example.healthcare_back.dto.response.board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.board.BoardEntity;
import com.example.healthcare_back.entity.board.BoardFileContentsEntity;
import com.example.healthcare_back.entity.board.CommentEntity;

import lombok.Getter;

// 게시물 내용 불러오기 Response DTO

@Getter
public class GetBoardResponseDto extends ResponseDto {
    private Integer boardNumber;
    private String boardTitle;
    private String userId;
    private LocalDateTime boardUploadDate;
    private String boardContents;
    private String youtubeVideoLink;
    private String boardFileContents;
    private Integer boardViewCount;
    private Integer boardLikeCount;
    private List<String> commentList;
    
    public GetBoardResponseDto(String code, String message, BoardEntity boardEntity, BoardFileContentsEntity boardFileContentsEntity, List<CommentEntity> commentEntities) {
        super(code, message);
        List<String> commentList = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntities) {
            String comment = commentEntity.getCommentContents();
            commentList.add(comment);
        }

        this.boardNumber = boardEntity.getBoardNumber();
        this.boardTitle = boardEntity.getBoardTitle();
        this.userId = boardEntity.getUserId();
        this.boardUploadDate = boardEntity.getBoardUploadDate();
        this.boardContents = boardEntity.getBoardContents();
        this.youtubeVideoLink = boardEntity.getYoutubeVideoLink();
        this.boardFileContents = boardFileContentsEntity != null ?  boardFileContentsEntity.getBoardFileContents() : null;
        this.boardViewCount = boardEntity.getBoardViewCount();
        this.boardLikeCount = boardEntity.getBoardLikeCount();
    }

    public static ResponseEntity<GetBoardResponseDto> success(BoardEntity boardEntity, BoardFileContentsEntity boardFileContentsEntity, List<CommentEntity> commentEntities) {
        GetBoardResponseDto responseBody = new GetBoardResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, boardEntity, boardFileContentsEntity, commentEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistBoard() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_BOARD, ResponseMessage.NO_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}