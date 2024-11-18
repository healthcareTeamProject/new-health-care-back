package com.example.healthcare_back.entity.board;

import java.math.BigDecimal;

import com.example.healthcare_back.dto.request.board.PatchBoardRequestDto;
import com.example.healthcare_back.dto.request.board.PostBoardRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "boardHealthMap")
@Table(name = "board_health_map")
public class BoardHealthMapEntity {

    @Id
    private Integer boardNumber;

    @Column(precision = 6, scale = 9)
    private BigDecimal mapLat;

    @Column(precision = 6, scale = 9)
    private BigDecimal mapLng;

    public BoardHealthMapEntity(PostBoardRequestDto dto, Integer boardNumber) {
        this.boardNumber = boardNumber;
        this.mapLat = dto.getMapLat();
        this.mapLng = dto.getMapLng();
    }

    public BoardHealthMapEntity(PatchBoardRequestDto dto, Integer boardNumber) {
        this.boardNumber = boardNumber;
        this.mapLat = dto.getMapLat();
        this.mapLng = dto.getMapLng();
    }

}
