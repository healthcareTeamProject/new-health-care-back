package com.example.healthcare_back.dto.response.board;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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
    private final String boardUploadDate;
    private final String boardContents;
    private final String youtubeVideoLink;
    private final String boardFileContents;
    private final Integer boardViewCount;
    private final Integer boardLikeCount;
    private final List<String> commentList = new ArrayList<>(); // 리스트 초기화
    private final Integer commentCount;

    public GetBoardResponseDto(BoardEntity boardEntity, List<CommentEntity> commentEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        
        // 댓글 내용을 리스트에 추가
        for (CommentEntity commentEntity : commentEntities) {
            this.commentList.add(commentEntity.getCommentContents());
        }

        // 업로드 시간 설정
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String boardUploadDate = simpleDateFormat.format(now);

        // 게시판 정보 설정
        this.boardNumber = boardEntity.getBoardNumber();
        this.boardTitle = boardEntity.getBoardTitle();
        this.nickname = boardEntity.getNickname();
        this.boardUploadDate = boardUploadDate;
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