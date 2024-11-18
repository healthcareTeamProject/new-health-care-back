package com.example.healthcare_back.service.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.healthcare_back.dto.request.board.PatchBoardRequestDto;
import com.example.healthcare_back.dto.request.board.PatchCommentRequestDto;
import com.example.healthcare_back.dto.request.board.PostBoardRequestDto;
import com.example.healthcare_back.dto.request.board.PostCommentRequestDto;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.dto.response.board.GetBoardCategoryResponseDto;
import com.example.healthcare_back.dto.response.board.GetBoardListResponseDto;
import com.example.healthcare_back.dto.response.board.GetBoardResponseDto;
import com.example.healthcare_back.dto.response.board.GetBoardTagResponseDto;
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

import jakarta.transaction.Transactional;
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
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList() {

        List<BoardEntity> boardList;
        List<GetBoardResponseDto> boardResponseList = new ArrayList<>();
        
        try {
            // 최신순으로 모든 게시물 조회
            boardList = boardRepository.findAllByOrderByBoardUploadDateDesc();
        
            // 각 게시물에 대한 댓글, 첨부파일 리스트 조회 (최신순) 및 응답 생성
            for (BoardEntity boardEntity : boardList) {
                List<CommentEntity> comments = commentRepository.findByBoardNumberOrderByCommentDateDesc(boardEntity.getBoardNumber());
                List<BoardFileContentsEntity> boardFileContents = boardFileContentsRepository.findByBoardNumberOrderByBoardFileNumberAsc(boardEntity.getBoardNumber());

                // 댓글이 없으면 빈 리스트로 설정
                if (comments == null) {
                    comments = new ArrayList<>();
                }

                // 파일이 없을 때 빈 리스트 반환
                if (boardFileContents == null) {
                boardFileContents = new ArrayList<>();
                }

                GetBoardResponseDto boardResponse = new GetBoardResponseDto(
                        ResponseCode.SUCCESS,
                        ResponseMessage.SUCCESS,
                        boardEntity,
                        boardFileContents, // boardFileContentsEntity가 없으면 null
                        comments
                );
                boardResponseList.add(boardResponse);
            }
        
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 성공적인 응답
        return GetBoardListResponseDto.success(boardResponseList);

    }

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {

        BoardEntity boardEntity;
        List<BoardFileContentsEntity> boardFileContentsEntities;
        List<CommentEntity> commentEntities;
        
        try {
            // 게시물 번호와 연결된 게시물 조회, 없으면 오류 반환
            boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) {
                return GetBoardResponseDto.noExistBoard();
            }

            // 댓글을 최신순으로 조회, 없으면 빈 리스트로 초기화
            commentEntities = commentRepository.findByBoardNumberOrderByCommentDateDesc(boardNumber);
            if (commentEntities == null) {
                commentEntities = new ArrayList<>();  // 댓글이 없을 때 빈 리스트 반환
            }

            // 파일을 파일 번호 순서로 조회, 없으면 빈 리스트로 초기화
            boardFileContentsEntities = boardFileContentsRepository.findByBoardNumberOrderByBoardFileNumberAsc(boardNumber);
            if (boardFileContentsEntities == null) {
                boardFileContentsEntities = new ArrayList<>();  // 파일이 없을 때 빈 리스트 반환
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 성공적인 응답
        return GetBoardResponseDto.success(boardEntity, boardFileContentsEntities, commentEntities);

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

            // 각 게시물에 대한 댓글, 파일 리스트 조회 및 응답 생성
            for (BoardEntity boardEntity : boardList) {
                List<CommentEntity> comments = commentRepository.findByBoardNumber(boardEntity.getBoardNumber());
                List<BoardFileContentsEntity> boardFileContents = boardFileContentsRepository.findByBoardNumberOrderByBoardFileNumberAsc(boardEntity.getBoardNumber());
                GetBoardResponseDto boardResponse = new GetBoardResponseDto(
                        ResponseCode.SUCCESS,
                        ResponseMessage.SUCCESS,
                        boardEntity,
                        boardFileContents,
                        comments
                );
                boardResponseList.add(boardResponse);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 성공적인 응답
        return GetBoardListResponseDto.success(boardResponseList);

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
    
        // 성공적인 응답
        return GetCommentListResponseDto.success(commentList);

    }

    @Override
    public ResponseEntity<? super GetBoardCategoryResponseDto> getBoardCategory(String boardCategory) {
        
        List<BoardEntity> boardList;
        List<GetBoardResponseDto> boardResponseList = new ArrayList<>();
        
        try {
            // 카테고리별 최신순으로 게시물 조회
            boardList = boardRepository.findByBoardCategoryOrderByBoardUploadDateDesc(boardCategory);
        
            // 각 게시물에 대한 댓글, 첨부파일 리스트 조회 (최신순) 및 응답 생성
            for (BoardEntity boardEntity : boardList) {
                List<CommentEntity> comments = commentRepository.findByBoardNumberOrderByCommentDateDesc(boardEntity.getBoardNumber());
                List<BoardFileContentsEntity> boardFileContents = boardFileContentsRepository.findByBoardNumberOrderByBoardFileNumberAsc(boardEntity.getBoardNumber());

                // 댓글이 없으면 빈 리스트로 설정
                if (comments == null) {
                    comments = new ArrayList<>();
                }

                // 파일이 없을 때 빈 리스트 반환
                if (boardFileContents == null) {
                boardFileContents = new ArrayList<>();
                }

                GetBoardResponseDto boardResponse = new GetBoardResponseDto(
                        ResponseCode.SUCCESS,
                        ResponseMessage.SUCCESS,
                        boardEntity,
                        boardFileContents,
                        comments
                );
                boardResponseList.add(boardResponse);
            }
        
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 성공적인 응답
        return GetBoardCategoryResponseDto.success(boardResponseList);

    }

    @Override
    public ResponseEntity<? super GetBoardTagResponseDto> getBoardTag(String boardTag) {
        
        List<BoardEntity> boardList;
        List<GetBoardResponseDto> boardResponseList = new ArrayList<>();
        
        try {
            // 최신순으로 모든 게시물 조회
            boardList = boardRepository.findByBoardTagOrderByBoardUploadDateDesc(boardTag);
        
            // 각 게시물에 대한 댓글, 첨부파일 리스트 조회 (최신순) 및 응답 생성
            for (BoardEntity boardEntity : boardList) {
                List<CommentEntity> comments = commentRepository.findByBoardNumberOrderByCommentDateDesc(boardEntity.getBoardNumber());
                List<BoardFileContentsEntity> boardFileContents = boardFileContentsRepository.findByBoardNumberOrderByBoardFileNumberAsc(boardEntity.getBoardNumber());

                // 댓글이 없으면 빈 리스트로 설정
                if (comments == null) {
                    comments = new ArrayList<>();
                }

                // 파일이 없을 때 빈 리스트 반환
                if (boardFileContents == null) {
                boardFileContents = new ArrayList<>();
                }

                GetBoardResponseDto boardTagResponse = new GetBoardResponseDto(
                        ResponseCode.SUCCESS,
                        ResponseMessage.SUCCESS,
                        boardEntity,
                        boardFileContents,
                        comments
                );
                boardResponseList.add(boardTagResponse);
            }
        
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 성공적인 응답
        return GetBoardTagResponseDto.success(boardResponseList);

    }

    @Override
    public ResponseEntity<? super ResponseDto> postBoard(PostBoardRequestDto dto, 
    String userId) {

        try {

            boolean existedUser = customerRepository.existsByUserId(userId);
            if (!existedUser) return ResponseDto.noExistUserId();
            
            BoardEntity boardEntities = new BoardEntity(dto, userId);
            boardRepository.save(boardEntities);

            // 해당하는 게시물 번호에 파일 저장
            Integer boardNumber = boardEntities.getBoardNumber();

            // 파일 업로드
            List<String> boardFileContentsList = dto.getBoardFileContents();
            

            if (boardFileContentsList != null && !boardFileContentsList.isEmpty()) {
                    for (String boardFileContents : boardFileContentsList) {
                        BoardFileContentsEntity boardFileContentsEntity = new BoardFileContentsEntity(boardNumber, boardFileContents);
                        boardFileContentsRepository.save(boardFileContentsEntity);
                }
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
    public ResponseEntity<ResponseDto> patchBoard(PatchBoardRequestDto dto, 
    Integer boardNumber, String userId) {

        try {
            // 게시물 번호로 게시글 조회
            BoardEntity boardEntities = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntities == null) {
                return ResponseDto.noExistBoard(); // 게시물이 없는 경우 처리
            }
    
            // 작성자와 수정하려는 유저가 같은지 확인
            if (!boardEntities.getUserId().equals(userId)) {
                return ResponseDto.noPermission(); // 권한 없음 응답
            }
    
            // 작성자가 맞다면 게시물 수정
            boardEntities.update(dto, boardNumber, userId);
            boardRepository.save(boardEntities);

            // 파일 리스트 수정
            List<String> boardFileContentsList = dto.getBoardFileContents();
            boardFileContentsRepository.deleteByBoardNumber(boardNumber);

            if (boardFileContentsList != null && !boardFileContentsList.isEmpty()) {
                    for (String boardFileContents : boardFileContentsList) {
                        BoardFileContentsEntity boardFileContentsEntity = new BoardFileContentsEntity(boardNumber, boardFileContents);
                        boardFileContentsRepository.save(boardFileContentsEntity);
                }
            }
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    
        // 성공적인 응답
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> postComment(PostCommentRequestDto dto, 
    Integer boardNumber, String userId) {

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
    public ResponseEntity<ResponseDto> patchComment(PatchCommentRequestDto dto, 
    Integer boardNumber, Integer commentNumber, String userId) {

        try {
            // 게시글 번호로 게시글 조회
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) {
                return ResponseDto.noExistBoard();
            }
    
            // 댓글 번호로 댓글 조회
            CommentEntity commentEntity = commentRepository.findByCommentNumber(commentNumber);
            if (commentEntity == null) {
                return ResponseDto.noExistComment();
            }
    
            // 작성자 검증: 댓글 작성자와 요청한 유저가 동일한지 확인
            if (!commentEntity.getUserId().equals(userId)) {
                return ResponseDto.noPermission(); // 권한 없음 응답
            }
    
            // 작성자라면 댓글 내용 수정
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
    @Transactional
    public ResponseEntity<ResponseDto> deleteBoard(Integer boardNumber, String userId) {

        try {
            // 게시물 번호로 게시물 조회
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) {
                return ResponseDto.noExistBoard();
            }
    
            // 작성자와 요청한 유저가 동일한지 확인
            if (!boardEntity.getUserId().equals(userId)) {
                return ResponseDto.noPermission(); // 권한 없음 응답
            }
    
            // 작성자라면 삭제 진행
            // board_health_map 테이블의 관련 데이터 삭제
            boardHealthMapRepository.deleteByBoardNumber(boardNumber);
    
            // board_file_contents 테이블의 관련 데이터 삭제
            boardFileContentsRepository.deleteByBoardNumber(boardNumber);
    
            // 게시물과 연관된 댓글 삭제
            commentRepository.deleteByBoardNumber(boardNumber);
    
            // board 테이블의 게시물 삭제
            boardRepository.delete(boardEntity);
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    
        // 성공적인 응답
        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<ResponseDto> deleteComment(Integer boardNumber, 
    Integer commentNumber, String userId) {
        
        try {
            // 게시글 번호로 게시글 조회
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) {
                return ResponseDto.noExistBoard();
            }
    
            // 댓글 번호로 댓글 조회
            CommentEntity commentEntity = commentRepository.findByCommentNumber(commentNumber);
            if (commentEntity == null) {
                return ResponseDto.noExistComment();
            }
    
            // 작성자 검증: 댓글 작성자와 요청한 유저가 동일한지 확인
            if (!commentEntity.getUserId().equals(userId)) {
                return ResponseDto.noPermission(); // 권한 없음 응답
            }
    
            // 작성자일 경우에만 댓글 삭제
            commentRepository.delete(commentEntity);
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    
        // 성공적인 응답
        return ResponseDto.success();
    }

    private Map<String, Map<Integer, Boolean>> boardLikesCache = new HashMap<>();
    private Map<String, Map<Integer, Boolean>> commentLikesCache = new HashMap<>();

    @Override
    public ResponseEntity<? super ResponseDto> putBoardLike(Integer boardNumber, String userId) {

        try {
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) {
                return ResponseDto.noExistBoard();
            }

            // 유저별 좋아요 상태 가져오기
            Map<Integer, Boolean> userBoardLikes = boardLikesCache.getOrDefault(userId, new HashMap<>());

            // 현재 유저가 이미 좋아요를 눌렀는지 확인
            if (userBoardLikes.getOrDefault(boardNumber, false)) {
                boardEntity.decreaseLikeCount();
                userBoardLikes.put(boardNumber, false);
            } else {
                boardEntity.increaseLikeCount();
                userBoardLikes.put(boardNumber, true);
            }

            boardLikesCache.put(userId, userBoardLikes); // 캐시 갱신
            boardRepository.save(boardEntity);

            return ResponseDto.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super ResponseDto> increaseViewCount(Integer boardNumber) {

        try {
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) {
                return ResponseDto.noExistBoard();
            }
    
            // 조회수 증가
            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 성공적인 응답
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super ResponseDto> putCommentLike(Integer commentNumber, String userId) {

        try {
            CommentEntity commentEntity = commentRepository.findByCommentNumber(commentNumber);
            if (commentEntity == null) {
                return ResponseDto.noExistComment();
            }

            // 유저별 댓글 좋아요 상태 가져오기
            Map<Integer, Boolean> userCommentLikes = commentLikesCache.getOrDefault(userId, new HashMap<>());

            // 현재 유저가 이미 좋아요를 눌렀는지 확인
            if (userCommentLikes.getOrDefault(commentNumber, false)) {
                commentEntity.decreaseLikeCount();
                userCommentLikes.put(commentNumber, false);
            } else {
                commentEntity.increaseLikeCount();
                userCommentLikes.put(commentNumber, true);
            }

            commentLikesCache.put(userId, userCommentLikes); // 캐시 갱신
            commentRepository.save(commentEntity);

            return ResponseDto.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
}