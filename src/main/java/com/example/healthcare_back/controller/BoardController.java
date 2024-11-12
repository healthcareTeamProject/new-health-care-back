package com.example.healthcare_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.healthcare_back.dto.request.board.PatchBoardRequestDto;
import com.example.healthcare_back.dto.request.board.PatchCommentRequestDto;
import com.example.healthcare_back.dto.request.board.PostBoardRequestDto;
import com.example.healthcare_back.dto.request.board.PostCommentRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.board.GetBoardCategoryResponseDto;
import com.example.healthcare_back.dto.response.board.GetBoardListResponseDto;
import com.example.healthcare_back.dto.response.board.GetBoardResponseDto;
import com.example.healthcare_back.dto.response.board.GetBoardTagResponseDto;
import com.example.healthcare_back.dto.response.board.GetCommentListResponseDto;
import com.example.healthcare_back.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 특정 게시물 조회
    @GetMapping("/{boardNumber}")
    public ResponseEntity<? super GetBoardResponseDto> getBoard(
            @PathVariable("boardNumber") Integer boardNumber) {
        return boardService.getBoard(boardNumber);
    }

    // 특정 게시물 댓글 목록 조회
    @GetMapping("/{boardNumber}/comment-list")
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(
            @PathVariable("boardNumber") Integer boardNumber) {
        return boardService.getCommentList(boardNumber);
    }

    // 모든 게시물 리스트 조회
    @GetMapping(value = {"", "/"})
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList() {
        ResponseEntity<? super GetBoardListResponseDto> response = boardService.getBoardList();
        return response;
    }

    // 특정 사용자의 게시물 리스트 조회
    @GetMapping("/user")
    public ResponseEntity<? super GetBoardListResponseDto> getUserBoardList(
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetBoardListResponseDto> response = boardService.getUserBoardList(userId);
        return response;
    }

    // 게시물 작성
    @PostMapping(value = { "", "/" })
    public ResponseEntity<? super ResponseDto> postBoard(
            @RequestBody @Valid PostBoardRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super ResponseDto> response = boardService.postBoard(requestBody, userId);
        return response;
    }

    // 게시물 수정
    @PatchMapping("/{boardNumber}")
    public ResponseEntity<? super ResponseDto> patchBoard(
            @PathVariable("boardNumber") Integer boardNumber,
            @RequestBody @Valid PatchBoardRequestDto requestBody,
            @AuthenticationPrincipal String userId // userId를 전달하는 경우
    ) {
        return boardService.patchBoard(requestBody, boardNumber, userId);
    }

    // 게시물 삭제
    @DeleteMapping("/{boardNumber}")
    public ResponseEntity<? super ResponseDto> deleteBoard(
            @PathVariable("boardNumber") Integer boardNumber,
            @AuthenticationPrincipal String userId // 권한 확인을 위한 userId를 전달하는 경우
    ) {
        return boardService.deleteBoard(boardNumber, userId);
    }

    // 댓글 작성
    @PostMapping("/{boardNumber}/comments")
    public ResponseEntity<? super ResponseDto> postComment(
            @PathVariable("boardNumber") Integer boardNumber,
            @RequestBody @Valid PostCommentRequestDto requestBody,
            @AuthenticationPrincipal String userId // 요청에서 userId 가져오기
    ) {
        return boardService.postComment(requestBody, boardNumber, userId);
    }

    // 댓글 수정
    @PatchMapping("/{boardNumber}/comments/{commentNumber}")
    public ResponseEntity<? super ResponseDto> patchComment(
            @PathVariable("boardNumber") Integer boardNumber,
            @PathVariable("commentNumber") Integer commentNumber,
            @RequestBody @Valid PatchCommentRequestDto requestBody,
            @AuthenticationPrincipal String userId // userId 전달
    ) {
        return boardService.patchComment(requestBody, boardNumber, commentNumber, userId);
    }

    // 댓글 삭제
    @DeleteMapping("/{boardNumber}/comments/{commentNumber}")
    public ResponseEntity<? super ResponseDto> deleteComment(
            @PathVariable("boardNumber") Integer boardNumber,
            @PathVariable("commentNumber") Integer commentNumber,
            @AuthenticationPrincipal String userId // 권한 확인을 위한 userId
    ) {
        return boardService.deleteComment(boardNumber, commentNumber, userId);
    }

   // 좋아요 추가/취소 (게시물)
   @PutMapping("/{boardNumber}/like")
   public ResponseEntity<? super ResponseDto> likeBoard(
           @PathVariable Integer boardNumber
   ) {
       return boardService.putBoardLike(boardNumber);
   }

   // 조회수 증가 (게시물)
   @PutMapping("/{boardNumber}/view")
   public ResponseEntity<? super ResponseDto> increaseViewCount(
           @PathVariable Integer boardNumber
   ) {
       return boardService.increaseViewCount(boardNumber);
   }

   // 좋아요 추가/취소 (댓글)
   @PutMapping("/{boardNumber}/comments/{commentNumber}/like")
   public ResponseEntity<? super ResponseDto> likeComment(
           @PathVariable Integer boardNumber,
           @PathVariable Integer commentNumber
   ) {
       return boardService.putCommentLike(commentNumber);
   }
}
