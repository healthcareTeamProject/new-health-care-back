package com.example.healthcare_back.dto.request.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import jakarta.persistence.Column;

// 회원정보 수정 Request Body DTO

@Getter
@Setter
@NoArgsConstructor
public class PatchUserThreeMajorLiftRequestDto {

    @Column(precision = 5, scale = 1)
    private BigDecimal deadlift;
    @Column(precision = 5, scale = 1)
    private BigDecimal benchPress;
    @Column(precision = 5, scale = 1)
    private BigDecimal squat;
    
}
