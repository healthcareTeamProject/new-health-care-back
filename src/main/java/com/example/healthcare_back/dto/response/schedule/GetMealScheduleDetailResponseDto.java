package com.example.healthcare_back.dto.response.schedule;

import java.math.BigDecimal;

import com.example.healthcare_back.entity.schedule.MealScheduleDetailEntity;

import lombok.Getter;

@Getter
public class GetMealScheduleDetailResponseDto {
    
    private String mealName; // 식품명
    private BigDecimal mealKcal; // 칼로리
    private Integer mealCount; // 개수

     // MealScheduleDetailEntity 객체를 DTO로 변환하는 생성자
    public GetMealScheduleDetailResponseDto(MealScheduleDetailEntity mealScheduleDetailEntity) {
        this.mealName = mealScheduleDetailEntity.getMealName();
        this.mealKcal = mealScheduleDetailEntity.getMealKcal();
        this.mealCount = mealScheduleDetailEntity.getMealCount();
    }

}
