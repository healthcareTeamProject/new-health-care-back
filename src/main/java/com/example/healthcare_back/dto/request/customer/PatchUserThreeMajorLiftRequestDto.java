package com.example.healthcare_back.dto.request.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PatchUserThreeMajorLiftRequestDto {
    
    @NotBlank
    private String userId;
    @Column(precision = 5, scale = 1)
    private BigDecimal deadlift;
    @Column(precision = 5, scale = 1)
    private BigDecimal benchPress;
    @Column(precision = 5, scale = 1)
    private BigDecimal squat;

}
