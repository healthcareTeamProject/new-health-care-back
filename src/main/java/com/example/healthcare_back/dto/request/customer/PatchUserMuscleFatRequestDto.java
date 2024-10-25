package com.example.healthcare_back.dto.request.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchUserMuscleFatRequestDto {
    
    @NotBlank
    private Double weight;
    private Double skeletalMuscleMass;
    private Double bodyFatMass;

}
