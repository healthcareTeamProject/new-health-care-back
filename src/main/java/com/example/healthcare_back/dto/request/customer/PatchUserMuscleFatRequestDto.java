package com.example.healthcare_back.dto.request.customer;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class PatchUserMuscleFatRequestDto {
    
    @NotBlank
    private String userId;
    @NotBlank @Column(precision = 5, scale = 1)
    private BigDecimal weight;
    @Column(precision = 5, scale = 1)
    private BigDecimal skeletalMuscleMass;
    @Column(precision = 5, scale = 1)
    private BigDecimal bodyFatMass;

}
