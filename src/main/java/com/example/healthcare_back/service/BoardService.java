package com.example.healthcare_back.service;

import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.request.board.PatchBoardRequestDto;
import com.example.healthcare_back.dto.request.board.PatchCommentRequestDto;
import com.example.healthcare_back.dto.request.board.PostBoardRequestDto;
import com.example.healthcare_back.dto.request.board.PostCommentRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.board.GetBoardListResponseDto;
import com.example.healthcare_back.dto.response.board.GetBoardResponseDto;
import com.example.healthcare_back.dto.response.board.GetCommentListResponseDto;

public interface BoardService {

<<<<<<< HEAD
public interface BoardService {

    // 게시물 조회
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);

    // 게시글 목록 조회
    ResponseEntity<? super GetBoardListResponseDto> getBoardList(); 

    // 게시물 작성
    ResponseEntity<ResponseDto> postBoard(PostBoardRequestDto dto, String nickName);

    // 게시물 수정
    ResponseEntity<ResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber, String userId); 

    // 게시글 삭제
    ResponseEntity<ResponseDto> deleteBoard(Integer boardNumber, String userId);

    // 댓글 작성
    ResponseEntity<ResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber, String userId);

    // 댓글 목록 조회
    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber); 

    // 댓글 수정
    ResponseEntity<ResponseDto> patchComment(PatchCommentRequestDto dto, Integer boardNumber, Integer commentNumber, String userId);
    
    // 댓글 삭제
    ResponseEntity<ResponseDto> deleteComment(Integer boardNumber, Integer commentNumber, String userId);
=======
    // 게시물 조회
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);

    // 게시글 목록 조회
    ResponseEntity<? super GetBoardListResponseDto> getBoardList();

    // 댓글 목록 조회
    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber);

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
>>>>>>> 59ec9a791fce95275047e79dddde2077520ea816

}
