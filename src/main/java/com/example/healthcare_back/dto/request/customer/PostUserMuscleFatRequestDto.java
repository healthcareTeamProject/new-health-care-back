package com.example.healthcare_back.dto.request.customer;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 회원 신체정보 조회 Request Body DTO

@Getter
@Setter
@NoArgsConstructor
public class PostUserMuscleFatRequestDto {

    @NotBlank
    private String userId;
    @NotNull 
    @Column(precision = 5, scale = 1)
    private BigDecimal weight;
    @Column(precision = 5, scale = 1)
    private BigDecimal skeletalMuscleMass;
    @Column(precision = 5, scale = 1)
    private BigDecimal bodyFatMass;

}
