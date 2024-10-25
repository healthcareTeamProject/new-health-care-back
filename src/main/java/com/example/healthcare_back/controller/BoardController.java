package com.example.healthcare_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.healthcare_back.dto.request.board.PatchBoardRequestDto;
import com.example.healthcare_back.dto.request.board.PatchCommentRequestDto;
import com.example.healthcare_back.dto.request.board.PostBoardRequestDto;
import com.example.healthcare_back.dto.request.board.PostCommentRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.board.GetBoardResponseDto;
import com.example.healthcare_back.dto.response.board.GetCommentListResponseDto;
import com.example.healthcare_back.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardService boardService;

    @GetMapping("/{boardNumber}")
    public ResponseEntity<? super GetBoardResponseDto> getBoard(
        @PathVariable("boardNumber") Integer boardNumber
    ) {
        return boardService.getBoard(boardNumber); // 특정 게시물을 조회합니다.
    }

    @GetMapping("/{boardNumber}/comment-list")
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(
        @PathVariable("boardNumber") Integer boardNumber
    ) {
        return boardService.getCommentList(boardNumber); // 특정 게시물의 댓글 목록을 조회합니다.
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<ResponseDto> postBoard(
        @RequestBody @Valid PostBoardRequestDto requestBody,
        @RequestParam String userId // 요청에서 userId를 가져오는 경우
    ) {
        ResponseEntity<ResponseDto> response = boardService.postBoard(requestBody, userId);
        return response; // 게시물을 생성합니다.
    }

    @PatchMapping("/{boardNumber}")
    public ResponseEntity<ResponseDto> patchBoard(
        @PathVariable("boardNumber") Integer boardNumber,
        @RequestBody @Valid PatchBoardRequestDto requestBody,
        @RequestParam String userId // userId를 전달하는 경우
    ) {
        return boardService.patchBoard(requestBody, boardNumber, userId); // 특정 게시물을 수정합니다.
    }

    @DeleteMapping("/{boardNumber}")
    public ResponseEntity<ResponseDto> deleteBoard(
        @PathVariable("boardNumber") Integer boardNumber,
        @RequestParam String userId // 권한 확인을 위한 userId를 전달하는 경우
    ) {
        return boardService.deleteBoard(boardNumber, userId); // 특정 게시물을 삭제합니다.
    }

    @PostMapping("/{boardNumber}/comments")
    public ResponseEntity<ResponseDto> postComment(
        @PathVariable("boardNumber") Integer boardNumber,
        @RequestBody @Valid PostCommentRequestDto requestBody,
        @RequestParam String userId // 요청에서 userId 가져오기
    ) {
        return boardService.postComment(requestBody, boardNumber, userId); // 댓글 추가
    }

    // 댓글 수정
    @PatchMapping("/{boardNumber}/comments/{commentNumber}")
    public ResponseEntity<ResponseDto> patchComment(
        @PathVariable("boardNumber") Integer boardNumber,
        @PathVariable("commentNumber") Integer commentNumber,
        @RequestBody @Valid PatchCommentRequestDto requestBody,
        @RequestParam String userId // userId 전달
    ) {
        return boardService.patchComment(requestBody, boardNumber, commentNumber, userId); // 댓글 수정
    }

    // 댓글 삭제
    @DeleteMapping("/{boardNumber}/comments/{commentNumber}")
    public ResponseEntity<ResponseDto> deleteComment(
        @PathVariable("boardNumber") Integer boardNumber,
        @PathVariable("commentNumber") Integer commentNumber,
        @RequestParam String userId // 권한 확인을 위한 userId
    ) {
        return boardService.deleteComment(boardNumber, commentNumber, userId); // 댓글 삭제
    }
}
