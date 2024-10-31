package com.example.healthcare_back.dto.request.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

// 회원정보 수정 Request Body DTO

@Getter
@Setter
@NoArgsConstructor
public class PatchUserMuscleFatRequestDto {

    @NotNull
    @Column(precision = 5, scale = 1)
    private BigDecimal weight;
    @Column(precision = 5, scale = 1)
    private BigDecimal skeletalMuscleMass;
    @Column(precision = 5, scale = 1)
    private BigDecimal bodyFatMass;
    
}
