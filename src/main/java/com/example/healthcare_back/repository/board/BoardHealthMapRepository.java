package com.example.healthcare_back.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.board.BoardHealthMapEntity;

@Repository
public interface BoardHealthMapRepository extends JpaRepository<BoardHealthMapEntity, Integer> {

    BoardHealthMapEntity findByBoardNumber(Integer boardNumber);

    void deleteByBoardNumber(Integer boardNumber);

}
