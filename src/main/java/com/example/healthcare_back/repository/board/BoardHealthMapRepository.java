package com.example.healthcare_back.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.board.BoardHealthMapEntity;

@Repository
public interface BoardHealthMapRepository extends JpaRepository<BoardHealthMapEntity, Integer> {

    BoardHealthMapEntity findByBoardNumber(Integer boardNumber); // 게시물 번호와 연관된 지도 데이터 조회

    void deleteByBoardNumber(Integer boardNumber); // 게시물 번호와 연관된 지도 데이터 삭제

}
