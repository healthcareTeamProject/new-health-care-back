package com.example.healthcare_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.healthcare_back.dto.request.board.PatchBoardRequestDto;
import com.example.healthcare_back.dto.request.board.PatchCommentRequestDto;
import com.example.healthcare_back.dto.request.board.PostBoardRequestDto;
import com.example.healthcare_back.dto.request.board.PostCommentRequestDto;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.dto.response.board.GetBoardListResponseDto;
import com.example.healthcare_back.dto.response.board.GetBoardResponseDto;
import com.example.healthcare_back.dto.response.board.GetCommentListResponseDto;
import com.example.healthcare_back.entity.board.BoardEntity;
import com.example.healthcare_back.entity.board.BoardFileContentsEntity;
import com.example.healthcare_back.entity.board.BoardHealthMapEntity;
import com.example.healthcare_back.entity.board.CommentEntity;
import com.example.healthcare_back.entity.customer.CustomerEntity;
import com.example.healthcare_back.repository.board.BoardFileContentsRepository;
import com.example.healthcare_back.repository.board.BoardHealthMapRepository;
import com.example.healthcare_back.repository.board.BoardRepository;
import com.example.healthcare_back.repository.board.CommentRepository;
import com.example.healthcare_back.repository.customer.CustomerRepository;
import com.example.healthcare_back.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardFileContentsRepository boardFileContentsRepository;
    private final BoardHealthMapRepository boardHealthMapRepository;
    private final CommentRepository commentRepository;
    private final CustomerRepository customerRepository;

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {

        BoardEntity boardEntity;
        BoardFileContentsEntity boardFileContentsEntity = null;
        List<CommentEntity> commentEntities;
        
        try {
        
            boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return GetBoardResponseDto.noExistBoard();
    
            // 댓글을 최신순으로 조회
            commentEntities = commentRepository.findByBoardNumberOrderByCommentDateDesc(boardNumber);
            if (commentEntities == null || commentEntities.isEmpty()) {
                return GetCommentListResponseDto.noExistComment();
            }
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    
        return GetBoardResponseDto.success(boardEntity, boardFileContentsEntity, commentEntities);

    }

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber) {

        List<CommentEntity> commentList;

        try {
            // 게시물 댓글 목록을 최신순으로 조회
            commentList = commentRepository.findByBoardNumberOrderByCommentDateDesc(boardNumber);
            if (commentList.isEmpty()) return GetCommentListResponseDto.noExistComment(); // 댓글이 없는 경우 처리
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    
        return GetCommentListResponseDto.success(commentList);

    }

    @Override
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList() {

        List<BoardEntity> boardList;
        List<GetBoardResponseDto> boardResponseList = new ArrayList<>();
        
        try {
            // 최신순으로 모든 게시물 조회
            boardList = boardRepository.findAllByOrderByBoardUploadDateDesc();
        
            // 각 게시물에 대한 댓글 리스트 조회 (최신순) 및 응답 생성
            for (BoardEntity boardEntity : boardList) {
                List<CommentEntity> comments = commentRepository.findByBoardNumberOrderByCommentDateDesc(boardEntity.getBoardNumber());
                GetBoardResponseDto boardResponse = new GetBoardResponseDto(
                        ResponseCode.SUCCESS,
                        ResponseMessage.SUCCESS,
                        boardEntity,
                        null, // boardFileContentsEntity가 없으면 null
                        comments
                );
                boardResponseList.add(boardResponse);
            }
        
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    
        return GetBoardListResponseDto.success(boardResponseList);

    }

    @Override
    public ResponseEntity<? super GetBoardListResponseDto> getUserBoardList(String userId) {
        
        List<BoardEntity> boardList;
        List<GetBoardResponseDto> boardResponseList = new ArrayList<>();

        try {
            // 사용자 존재 여부 확인
            CustomerEntity customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) {
                return ResponseDto.noExistUserId(); // 고객이 없을 경우 응답
            }

            // 특정 사용자가 작성한 모든 게시물을 최신순으로 조회
            boardList = boardRepository.findByUserIdOrderByBoardUploadDateDesc(userId);

            // 각 게시물에 대한 댓글 리스트 조회 및 응답 생성
            for (BoardEntity boardEntity : boardList) {
                List<CommentEntity> comments = commentRepository.findByBoardNumber(boardEntity.getBoardNumber());
                GetBoardResponseDto boardResponse = new GetBoardResponseDto(
                        ResponseCode.SUCCESS, 
                        ResponseMessage.SUCCESS, 
                        boardEntity, 
                        null, // boardFileContentsEntity가 없으면 null
                        comments
                );
                boardResponseList.add(boardResponse);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetBoardListResponseDto.success(boardResponseList);

    }

    @Override
    public ResponseEntity<? super ResponseDto> postBoard(PostBoardRequestDto dto, String userId) {

        try {

            boolean existedUser = customerRepository.existsByUserId(userId);
            if (!existedUser) return ResponseDto.noExistUserId();
            
            BoardEntity boardEntity = new BoardEntity(dto, userId);
            boardRepository.save(boardEntity);

            String boardFileContents = dto.getBoardFileContents();
            Integer boardNumber = boardEntity.getBoardNumber();
            if (boardFileContents != null) {
                BoardFileContentsEntity boardFileContentsEntity = new BoardFileContentsEntity(dto, boardNumber);
                boardFileContentsRepository.save(boardFileContentsEntity);
            }

            BoardHealthMapEntity boardHealthMapEntity = new BoardHealthMapEntity(dto, boardNumber);
            boardHealthMapRepository.save(boardHealthMapEntity);

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
            if (boardEntity == null) return ResponseDto.noExistBoard(); // 게시물이 없는경우 처리

            // 게시물 업로드
            boardEntity.update(dto, boardNumber, userId);
            boardRepository.save(boardEntity); // 게시물 수정

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
            if (boardEntity == null) return ResponseDto.noExistBoard(); // 게시물이 없는경우

            // 사용자 존재여부 확인
            boolean existedUser = customerRepository.existsByUserId(userId);
            if (!existedUser) return ResponseDto.noExistUserId();

            // 댓글 생성 후 저장
            CommentEntity commentEntity = new CommentEntity(dto, boardNumber, userId); // 메서드 호출
            commentRepository.save(commentEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 성공적인 응답
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchComment(PatchCommentRequestDto dto, Integer boardNumber, Integer commentNumber, String userId) {

        try {

            // 각각의 번호와 연결된 게시글과 댓글 조회
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.noExistBoard();

            CommentEntity commentEntity = commentRepository.findByCommentNumber(commentNumber);
            if (commentEntity == null) return ResponseDto.noExistComment();

            // 댓글 내용 수정
            commentEntity.update(dto, boardNumber, commentNumber, userId);
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
            if (boardEntity == null) return ResponseDto.noExistBoard();

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
            if (boardEntity == null) return ResponseDto.noExistBoard();

            CommentEntity commentEntity = commentRepository.findByCommentNumber(commentNumber);
            if (commentEntity == null) return ResponseDto.noExistComment();

            // 댓글 삭제
            commentRepository.delete(commentEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 성공적인 응답
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super ResponseDto> putBoardLike(Integer boardNumber) {

        try {
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.noExistBoard();

            // 좋아요 개수만 증가/감소
            if (boardEntity.getBoardLikeCount() > 0) {
                boardEntity.decreaseLikeCount(); // 좋아요 취소
            } else {
                boardEntity.increaseLikeCount(); // 좋아요 추가
            }

            boardRepository.save(boardEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super ResponseDto> increaseViewCount(Integer boardNumber) {

        try {
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.noExistBoard();
    
            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super ResponseDto> putCommentLike(Integer commentNumber) {

        try {
            CommentEntity commentEntity = commentRepository.findByCommentNumber(commentNumber);
            if (commentEntity == null) return ResponseDto.noExistComment();

            // 좋아요 개수만 증가/감소
            if (commentEntity.getCommentLikeCount() > 0) {
                commentEntity.decreaseLikeCount(); // 좋아요 취소
            } else {
                commentEntity.increaseLikeCount(); // 좋아요 추가
            }

            commentRepository.save(commentEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }



}