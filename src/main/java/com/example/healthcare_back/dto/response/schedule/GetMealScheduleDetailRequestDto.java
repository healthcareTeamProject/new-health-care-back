package com.example.healthcare_back.dto.response.schedule;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class GetMealScheduleDetailRequestDto {
    
    private Integer mealScheduleDetailNumber;
    private Integer mealScheduleNumber;
    private String mealName;
    private BigDecimal mealKcal;
    private Integer mealCount;


}
