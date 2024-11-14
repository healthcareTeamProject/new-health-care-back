package com.example.healthcare_back.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.board.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    boolean existsByBoardNumber(Integer boardNumber);
    BoardEntity findByBoardNumber(Integer boardNumber);

    List<BoardEntity> findByUserId(String userId); // 특정 사용자 작성 게시물 조회
    List<BoardEntity> findAllByOrderByBoardUploadDateDesc(); // 최신 날짜 순서로 게시물 조회
    List<BoardEntity> findByUserIdOrderByBoardUploadDateDesc(String userId); // 특정 사용자 게시물을 최신 날짜 순서로 조회

    List<BoardEntity> findByBoardCategoryOrderByBoardUploadDateDesc(String boardCategory); // 카테고리별 게시물 조회
    List<BoardEntity> findByBoardTagOrderByBoardUploadDateDesc(String boardTag); // 해시태그별 게시물 조회

}