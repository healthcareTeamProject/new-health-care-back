package com.example.healthcare_back.dto.request.customer;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kotlinx.datetime.LocalDateTime;
public class PostUserMuscleFatRequestDto {
    
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
