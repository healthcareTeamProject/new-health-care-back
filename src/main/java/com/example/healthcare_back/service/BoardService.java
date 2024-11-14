package com.example.healthcare_back.service;

import org.springframework.http.ResponseEntity;

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

public interface BoardService {

    // 게시글 목록 조회
    ResponseEntity<? super GetBoardListResponseDto> getBoardList();

    // 게시물 조회
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);

    // 게시글 목록 조회
    ResponseEntity<? super GetBoardListResponseDto> getUserBoardList(String userId);

    // 댓글 목록 조회
    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber);

    // 카테고리별 게시물 조회
    ResponseEntity<? super GetBoardCategoryResponseDto> getBoardCategory(String boardCategory);

    // 태그별 게시물 조회
    ResponseEntity<? super GetBoardTagResponseDto> getBoardTag(String boardTag);

    // 게시물 작성
    ResponseEntity<? super ResponseDto> postBoard(PostBoardRequestDto dto, String userId);

    // 게시물 수정
    ResponseEntity<? super ResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber, String userId);

    // 게시글 삭제
    ResponseEntity<? super ResponseDto> deleteBoard(Integer boardNumber, String userId);

    // 댓글 작성
    ResponseEntity<? super ResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber, String userId);

    // 댓글 수정
    ResponseEntity<? super ResponseDto> patchComment(PatchCommentRequestDto dto, Integer boardNumber, Integer commentNumber, String userId);

    // 댓글 삭제
    ResponseEntity<? super ResponseDto> deleteComment(Integer boardNumber, Integer commentNumber, String userId);

    // 게시글 좋아요 누르기
    ResponseEntity<? super ResponseDto> putBoardLike(Integer boardNumber, String userId);

    // 게시글 조회수 증가
    ResponseEntity<? super ResponseDto> increaseViewCount(Integer boardNumber);

    // 댓글 좋아요 누르기
    ResponseEntity<? super ResponseDto> putCommentLike(Integer commentNumber, String userId);

}
