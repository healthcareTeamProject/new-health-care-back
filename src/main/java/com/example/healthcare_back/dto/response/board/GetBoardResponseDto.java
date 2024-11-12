package com.example.healthcare_back.dto.response.board;

<<<<<<< HEAD
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
=======
import java.time.LocalDateTime;
import java.util.ArrayList;
>>>>>>> 59ec9a791fce95275047e79dddde2077520ea816
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.board.BoardEntity;
<<<<<<< HEAD
=======
import com.example.healthcare_back.entity.board.BoardFileContentsEntity;
>>>>>>> 59ec9a791fce95275047e79dddde2077520ea816
import com.example.healthcare_back.entity.board.CommentEntity;

import lombok.Getter;

// 게시물 내용 불러오기 Response DTO

@Getter
public class GetBoardResponseDto extends ResponseDto {
<<<<<<< HEAD
    
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

=======
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
>>>>>>> 59ec9a791fce95275047e79dddde2077520ea816
}