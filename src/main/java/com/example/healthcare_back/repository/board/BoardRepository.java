package com.example.healthcare_back.repository.board;

<<<<<<< HEAD
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.board.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    @Query(value =
        "SELECT " + 
            "B.board_number AS boardNumber, " +
            "B.board_title AS BoardTitle, " +
            "B.nickname AS Nickname, " +
            "B.board_upload_date AS BoardUploadDate, " +
            "B.board_view_count AS BoardViewCount " +
        "FROM Board AS B " +
        "INNER JOIN customer AS CU " + // customer 테이블과 조인
        "ON B.nickname = CU.nickname " + // user_id로 조인
        "WHERE B.board_number = ?1 " + // board_number로 필터링
        "ORDER BY B.Board_date ASC ", //  게시물 업로드 날짜순으로 정렬
        nativeQuery = true
    )
    boolean existsByBoardNumber(Integer boardNumber); // 게시물 번호에 해당하는 게시물 존재 여부 조회
    BoardEntity findByBoardNumber(Integer boardNumber); // 게시물 번호 조회
    List<BoardEntity> findAllByBoardNumber(Integer boardNumber); // 게시물 목록 조회
    BoardEntity deleteByBoardNumber(Integer boardNumber); // 해당하는 게시물 번호의 게시물 삭제
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.board.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    boolean existsByBoardNumber(Integer boardNumber);

    BoardEntity findByBoardNumber(Integer boardNumber);

>>>>>>> 59ec9a791fce95275047e79dddde2077520ea816
}