package com.example.healthcare_back.entity.board;

import java.util.List;

import com.example.healthcare_back.dto.request.board.PatchBoardRequestDto;
import com.example.healthcare_back.dto.request.board.PostBoardRequestDto;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "boardFileContents")
@Table(name = "board_file_contents")
public class BoardFileContentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardFileNumber;
    private Integer boardNumber;
    @Convert(converter = StringListConverter.class)
    private List<String> boardFileContents;

    public BoardFileContentsEntity(PostBoardRequestDto dto, Integer boardNumber) {
        this.boardNumber = boardNumber;
        this.boardFileContents = dto.getBoardFileContents();
    }

    public BoardFileContentsEntity(PatchBoardRequestDto dto, Integer boardNumber) {
        this.boardNumber = boardNumber;
        this.boardFileContents = dto.getBoardFileContents();
    }

    // 일반 생성자 생성
    public BoardFileContentsEntity(Integer boardNumber, String boardFileContents) {
        this.boardNumber = boardNumber;
        this.boardFileContents = List.of(boardFileContents);
    }
}
