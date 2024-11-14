package com.example.healthcare_back.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.board.BoardFileContentsEntity;

@Repository
public interface BoardFileContentsRepository extends JpaRepository<BoardFileContentsEntity, Integer> {

    BoardFileContentsEntity findByBoardNumber(Integer boardNumber); // 게시물 번호와 연관된 파일 데이터 조회

    void deleteByBoardNumber(Integer boardNumber); // 게시물 번호와 연관된 파일 데이터 삭제


}
