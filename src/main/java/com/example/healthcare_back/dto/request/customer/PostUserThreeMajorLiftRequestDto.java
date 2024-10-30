package com.example.healthcare_back.dto.request.customer;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// 회원 3대측정 정보 조회 Request Body DTO
import kotlinx.datetime.LocalDateTime;

public class PostUserThreeMajorLiftRequestDto {
    
    @Positive
    @Column(precision = 5, scale = 1)
    private BigDecimal deadlift;
    @Positive
    @Column(precision = 5, scale = 1)
    private BigDecimal benchPress;
    @Positive
    @Column(precision = 5, scale = 1)
    private BigDecimal squat;
    @NotNull 
    private LocalDateTime userThreeMajorLiftDate;

}
