package com.example.healthcare_back.dto.request.auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SignUpUserMuscleFatRequestDto {

    @NotBlank
    private String userId;
    @Positive
    @NotNull 
    @Column(precision = 5, scale = 1)
    private BigDecimal weight;
    @Positive
    @Column(precision = 5, scale = 1)
    private BigDecimal skeletalMuscleMass;
    @Positive
    @Column(precision = 5, scale = 1)
    private BigDecimal bodyFatMass;
    @NotNull 
    private LocalDateTime userMuscleFatDate;

}
