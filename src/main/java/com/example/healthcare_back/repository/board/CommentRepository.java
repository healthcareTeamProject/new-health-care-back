package com.example.healthcare_back.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.board.CommentEntity;

import jakarta.transaction.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
   
    @Query(value =
        "SELECT " + 
            "C.comment_number AS commentNumber, " +
            "C.board_number AS boardNumber, " +
            "C.comment_contents AS commentContents, " +
            "C.user_id AS userId, " +
            "C.comment_date AS commentDate, " +
            "C.comment_like_count AS commentLikeCount " +
        "FROM comment AS C " +
        "INNER JOIN customer AS CU " + // customer 테이블과 조인
        "ON C.user_id = CU.user_id " + // user_id로 조인
        "WHERE C.board_number = ?1 " + // board_number로 필터링
        "ORDER BY C.comment_date DESC", // 댓글 날짜로 정렬
        nativeQuery = true
    )
    List<CommentEntity> findCommentsByBoardNumber(Integer boardNumber); // 댓글 목록 반환
    List<CommentEntity> findByBoardNumber(Integer boardNumber); // 게시물 리스트 조회
    List<CommentEntity> findByBoardNumberOrderByCommentDateDesc(Integer boardNumber); // 게시물의 댓글을 업로드 날짜 순서로 조회
    CommentEntity findByCommentNumber(Integer commentNumber); // 댓글 리스트 조회

    @Transactional
    void deleteByBoardNumber(Integer boardNumber); // 해당하는 게시물 번호의 게시물 삭제
    
}