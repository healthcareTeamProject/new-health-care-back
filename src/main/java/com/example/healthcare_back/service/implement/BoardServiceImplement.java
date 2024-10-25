package com.example.healthcare_back.service.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.healthcare_back.dto.request.board.PatchBoardRequestDto;
import com.example.healthcare_back.dto.request.board.PatchCommentRequestDto;
import com.example.healthcare_back.dto.request.board.PostBoardRequestDto;
import com.example.healthcare_back.dto.request.board.PostCommentRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.board.GetBoardListResponseDto;
import com.example.healthcare_back.dto.response.board.GetBoardResponseDto;
import com.example.healthcare_back.dto.response.board.GetCommentListResponseDto;
import com.example.healthcare_back.entity.BoardEntity;
import com.example.healthcare_back.entity.CommentEntity;
import com.example.healthcare_back.repository.BoardRepository;
import com.example.healthcare_back.repository.CommentRepository;
import com.example.healthcare_back.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {
    
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
        public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {
            BoardEntity boardEntity;
            List<CommentEntity> commentList; // 변경된 타입으로 선언
    
            try {
            // 게시물 조회
            boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) {
                return GetBoardResponseDto.noExistBoard(); // 게시물이 없을 경우 처리
            }
        
            // 게시물에 대한 댓글 조회
            commentList = commentRepository.findByBoardNumber(boardNumber); // 수정된 메서드 호출
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 처리
        }

        // 성공적인 응답
        return GetBoardResponseDto.success(boardEntity, commentList);
    }

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber) {
        
        return null;

    }


    @Override
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList() {

        return null;

    }

    @Override
    public ResponseEntity<ResponseDto> postBoard(PostBoardRequestDto dto, String userId) {
        
        return null;

    }
    
    @Override
    public ResponseEntity<ResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber, String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patchBoard'");
    }

    @Override
    public ResponseEntity<ResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber, String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postBoard'");
    }

    @Override
    public ResponseEntity<ResponseDto> patchComment(PatchCommentRequestDto dto, Integer boardNumber,
            Integer commentNumber, String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patchComment'");
    }

    @Override
    public ResponseEntity<ResponseDto> deleteBoard(Integer boardNumber, String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteBoard'");
    }

    @Override
    public ResponseEntity<ResponseDto> deleteComment(Integer boardNumber, Integer commentNumber, String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteComment'");
    }


}
