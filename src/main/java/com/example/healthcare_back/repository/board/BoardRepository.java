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
    List<BoardEntity> findAllByOrderByBoardUploadDateDesc();
    List<BoardEntity> findByUserIdOrderByBoardUploadDateDesc(String userId);

}