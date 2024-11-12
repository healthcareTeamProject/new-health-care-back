package com.example.healthcare_back.service.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
import com.example.healthcare_back.entity.board.BoardEntity;
import com.example.healthcare_back.entity.board.BoardFileContentsEntity;
import com.example.healthcare_back.entity.board.BoardHealthMapEntity;
import com.example.healthcare_back.entity.board.CommentEntity;
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

            // 게시물 조회
            boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return GetBoardResponseDto.noExistBoard(); // 게시물이 없을 경우 처리

            // 게시물에 대한 댓글 조회
            commentEntities = commentRepository.findByBoardNumber(boardNumber);
            if (commentEntities == null) return GetCommentListResponseDto.noExistComment(); // 댓글이 없을 경우 처리

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 처리
        }

        return GetBoardResponseDto.success(boardEntity, boardFileContentsEntity, commentEntities);

    }

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber) {

        List<CommentEntity> commentList;

        try {

            // 게시물 댓글 목록 조회
            commentList = commentRepository.findByBoardNumber(boardNumber);
            if (commentList == null) return GetCommentListResponseDto.noExistComment(); // 댓글이 없는 경우 처리

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCommentListResponseDto.success(commentList);

    }

    @Override
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList() {

        List<BoardEntity> boardList;

        try {

            // 게시물 목록 조회
            boardList = boardRepository.findAll();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetBoardListResponseDto.success(boardList);

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
    public ResponseEntity<? super GetBoardCategoryResponseDto> getBoardCategory(String boardCategory) {
        
        List<BoardEntity> boardCategoryList;

        try {

            // 카테고리별 게시물 목록 조회
            boardCategoryList = boardRepository.findByBoardCategory(boardCategory);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 처리
        }

        return GetBoardCategoryResponseDto.success(boardCategoryList);

    }

    @Override
    public ResponseEntity<? super GetBoardTagResponseDto> getBoardTag(String boardTag) {
        
        List<BoardEntity> boardTagList;

        try {

            // 해시태그별 게시물 목록 조회
            boardTagList = boardRepository.findByBoardTag(boardTag);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 처리
        }

        return GetBoardTagResponseDto.success(boardTagList);

    }

}