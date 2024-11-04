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
import com.example.healthcare_back.repository.resultSet.BoardListResultSet;
import com.example.healthcare_back.repository.resultSet.CommentListResultSet;
import com.example.healthcare_back.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {
    
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {
        try {
            // 게시물 조회
            BoardEntity boardList = boardRepository.findByBoardNumber(boardNumber);
            if (boardList == null) {
                return GetBoardResponseDto.noExistBoard(); // 게시물이 없을 경우 처리
            }
        
            // 게시물에 대한 댓글 조회
            List<CommentEntity> commentList = commentRepository.findByBoardNumber(boardNumber);
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 처리
        }
        
        // 성공적인 응답
        BoardEntity boardList;
        List<CommentEntity> commentList;
        return GetBoardResponseDto.success(boardList, commentList);
    }

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber) {
        try {
            // 게시물 댓글 목록 조회
            List<CommentEntity> commentList = commentRepository.findByBoardNumber(boardNumber);
            if (commentList == null || commentList.isEmpty()) {
                return GetCommentListResponseDto.noExistComment();  // 댓글이 없는 경우 처리
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 성공적인 응답
        List<CommentListResultSet> commentList = null;
        return GetCommentListResponseDto.success(commentList);
        
    }


    @Override
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList() {
        try {
            // 게시물 목록 조회
            List<BoardEntity> boardList = boardRepository.findAll();
            if (boardList == null || boardList.isEmpty()) {
                return GetBoardListResponseDto.noExistBoard();  // 게시물 목록이 없는경우
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 성공적인 응답
        List<BoardListResultSet> boardList = null;
        return GetBoardListResponseDto.success(boardList);

    }

    @Override
    public ResponseEntity<ResponseDto> postBoard(PostBoardRequestDto dto, String userId) {
        try {
            // 새로운 게시물 생성
            BoardEntity boardEntity = new BoardEntity(dto, userId);  // 메서드 호출
            boardRepository.save(boardEntity);  // 새로운 게시물 저장

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 성공적인 응답
        return ResponseDto.success();

    }
    
    @Override
    public ResponseEntity<ResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber, String userId) {
        try {
            // 게시물 번호로 게시글 조회
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) {
                return ResponseDto.noExistBoard();  // 게시물이 없는경우
            }

            // 게시물 업로드
            boardEntity.update(dto);  // 메서드 호출
            boardRepository.save(boardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 성공적인 응답
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber, String userId) {
        try {
            // 게시물 번호로 게시글 조회
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) {
                return ResponseDto.noExistBoard();  // 게시물이 없는경우
            }

            // 댓글 생성 후 저장
            CommentEntity commentEntity = new CommentEntity(dto, boardNumber, userId);  // 메서드 호출
            commentRepository.save(commentEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 성공적인 응답
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchComment(PatchCommentRequestDto dto, Integer boardNumber,
            Integer commentNumber, String userId) {
            try {
                // 각각의 번호와 연결된 게시글과 댓글 조회
                BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
                if (boardEntity == null) {
                    return ResponseDto.noExistBoard();
                }
        
                CommentEntity commentEntity = commentRepository.findByCommentNumber(commentNumber);
                if (commentEntity == null) {
                    return ResponseDto.noExistComment();
                }
        
                // 댓글 내용 수정
                commentEntity.update(dto);  // 메서드 호출
                commentRepository.save(commentEntity);
        
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return ResponseDto.databaseError();
                }
            
            // 성공적인 응답
            return ResponseDto.success();
            
    }

    @Override
    public ResponseEntity<ResponseDto> deleteBoard(Integer boardNumber, String userId) {
        try {
            // 게시물 번호로 게시물 조회
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) {
                return ResponseDto.noExistBoard();
            }

            // 게시물과 연관된 댓글 삭제
            commentRepository.deleteByBoardNumber(boardNumber);
            boardRepository.delete(boardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 성공적인 응답
        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<ResponseDto> deleteComment(Integer boardNumber, Integer commentNumber, String userId) {
        try {
            // 연관된 번호로 게시글과 댓글 조회
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) {
                return ResponseDto.noExistBoard();
            }

            CommentEntity commentEntity = commentRepository.findByCommentNumber(commentNumber);
            if (commentEntity == null) {
                return ResponseDto.noExistComment();
            }

            // 댓글 삭제
            commentRepository.delete(commentEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 성공적인 응답
        return ResponseDto.success();
    }
}
