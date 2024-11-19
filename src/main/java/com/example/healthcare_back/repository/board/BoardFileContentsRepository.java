package com.example.healthcare_back.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.board.BoardFileContentsEntity;

import jakarta.transaction.Transactional;

@Repository
public interface BoardFileContentsRepository extends JpaRepository<BoardFileContentsEntity, Integer> {

    List<BoardFileContentsEntity> findByBoardNumberOrderByBoardFileNumberAsc(Integer boardNumber); // 파일 목록 데이터 조회

    @Transactional
    void deleteByBoardNumber(Integer boardNumber); // 게시물 번호와 연관된 파일 데이터 삭제

}
