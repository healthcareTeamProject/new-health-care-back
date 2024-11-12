package com.example.healthcare_back.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.board.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    boolean existsByBoardNumber(Integer boardNumber);

    BoardEntity findByBoardNumber(Integer boardNumber);

}