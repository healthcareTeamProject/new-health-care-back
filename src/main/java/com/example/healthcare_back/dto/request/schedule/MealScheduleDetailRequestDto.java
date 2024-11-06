package com.example.healthcare_back.dto.request.schedule;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MealScheduleDetailRequestDto {
    
    @NotBlank
    @JsonProperty("foodNm")
    private String mealName;
    @NotNull
    @JsonProperty("enerc") 
    private BigDecimal mealKcal;
    @NotNull
    private Integer mealCount;

}
