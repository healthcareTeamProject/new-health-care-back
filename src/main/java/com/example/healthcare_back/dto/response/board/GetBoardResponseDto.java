package com.example.healthcare_back.dto.response.board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.common.object.CommentList;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.board.BoardEntity;
import com.example.healthcare_back.entity.board.BoardFileContentsEntity;
import com.example.healthcare_back.entity.board.CommentEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

// 게시물 내용 불러오기 Response DTO

@Getter
public class GetBoardResponseDto extends ResponseDto {
    private Integer boardNumber;
    private String boardTitle;
    private String userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime boardUploadDate;
    private String boardContents;
    private String youtubeVideoLink;
    private String boardFileContents;
    private Integer boardViewCount;
    private Integer boardLikeCount;
    private List<CommentList> commentList; // 수정: List<CommentList>로 변경

    // 생성자 수정
    public GetBoardResponseDto(String code, String message, BoardEntity boardEntity, BoardFileContentsEntity boardFileContentsEntity, List<CommentEntity> commentEntities) {
        super(code, message);

        this.boardNumber = boardEntity.getBoardNumber();
        this.boardTitle = boardEntity.getBoardTitle();
        this.userId = boardEntity.getUserId();
        this.boardUploadDate = boardEntity.getBoardUploadDate();
        this.boardContents = boardEntity.getBoardContents();
        this.youtubeVideoLink = boardEntity.getYoutubeVideoLink();
        this.boardFileContents = boardFileContentsEntity != null ? boardFileContentsEntity.getBoardFileContents() : null;
        this.boardViewCount = boardEntity.getBoardViewCount();
        this.boardLikeCount = boardEntity.getBoardLikeCount();

        // commentList 초기화
        this.commentList = commentEntities != null && !commentEntities.isEmpty()
                ? commentEntities.stream()
                    .map(comment -> new CommentList(comment)) // CommentEntity를 CommentList로 변환
                    .collect(Collectors.toList())
                : new ArrayList<>(); // commentEntities가 null 또는 비어있는 경우 빈 리스트 반환
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