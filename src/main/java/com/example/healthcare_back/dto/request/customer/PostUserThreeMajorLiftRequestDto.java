package com.example.healthcare_back.dto.request.customer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// 회원 3대측정 정보 조회 Request Body DTO
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostUserThreeMajorLiftRequestDto {
    
    @NotNull
    private int userThreeMajorLiftNumber;
    @NotBlank
    private String userId;
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
    private LocalDateTime userMuscleFatDate;

}
