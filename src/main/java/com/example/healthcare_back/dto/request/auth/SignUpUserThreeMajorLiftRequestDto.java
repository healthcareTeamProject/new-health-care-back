package com.example.healthcare_back.dto.request.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// 회원 3대측정 정보 수정 Request Body DTO

@Getter
@Setter
@NoArgsConstructor
public class SignUpUserThreeMajorLiftRequestDto {
    
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
    private LocalDateTime userThreeMajorLiftDate;

}
