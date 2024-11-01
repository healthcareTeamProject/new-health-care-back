package com.example.healthcare_back.dto.request.customer;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

// 회원 3대측정 정보 조회 Request Body DTO
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostUserThreeMajorLiftRequestDto {
    
    @NotBlank
    private String userId;
    @Column(precision = 5, scale = 1)
    private BigDecimal deadlift;
    @Column(precision = 5, scale = 1)
    private BigDecimal benchPress;
    @Column(precision = 5, scale = 1)
    private BigDecimal squat;

}
