package com.example.healthcare_back.dto.response.board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.BoardEntity;
import com.example.healthcare_back.entity.CommentEntity;

import lombok.Getter;

// 게시물 내용 불러오기 Response DTO

@Getter
public class GetBoardResponseDto extends ResponseDto {
    
    private final Integer boardNumber;
    private final String boardTitle;
    private final String nickname;
    private final LocalDateTime boardUploadDate;
    private final String boardContents;
    private final String youtubeVideoLink;
    private final String boardFileContents;
    private final Integer boardViewCount;
    private final Integer boardLikeCount;
    private final List<String> commentList = new ArrayList<>(); // 초기화
    private final Integer commentCount;

    public GetBoardResponseDto(BoardEntity boardEntity, List<CommentEntity> commentEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        
        // 댓글 내용을 리스트에 추가
        for (CommentEntity commentEntity : commentEntities) {
            this.commentList.add(commentEntity.getCommentContents());
        }

        // 게시판 정보 설정
        this.boardNumber = boardEntity.getBoardNumber();
        this.boardTitle = boardEntity.getBoardTitle();
        this.nickname = boardEntity.getNickname();
        this.boardUploadDate = LocalDateTime.now();
        this.boardContents = boardEntity.getBoardContents();
        this.youtubeVideoLink = boardEntity.getYoutubeVideoLink();
        this.boardFileContents = boardEntity.getBoardFileContents();
        this.boardViewCount = boardEntity.getBoardViewCount();
        this.boardLikeCount = boardEntity.getBoardLikeCount();
        this.commentCount = boardEntity.getCommentCount();
    }

    public static ResponseEntity<GetBoardResponseDto> success(BoardEntity boardEntity, List<CommentEntity> commentEntities) {
        GetBoardResponseDto responseBody = new GetBoardResponseDto(boardEntity, commentEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
