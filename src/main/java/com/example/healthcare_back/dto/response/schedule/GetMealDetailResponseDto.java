package com.example.healthcare_back.dto.response.schedule;

import java.math.BigDecimal;

import com.example.healthcare_back.common.util.CustomBigDecimalSerializer;
import com.example.healthcare_back.entity.schedule.MealScheduleDetailEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;

@Getter
public class GetMealDetailResponseDto {

    private Integer mealScheduleDetailNumber;
    private String mealName;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal mealKcal;
    private Integer mealCount;

    public GetMealDetailResponseDto(MealScheduleDetailEntity detailEntity) {
        this.mealScheduleDetailNumber = detailEntity.getMealScheduleDetailNumber();
        this.mealName = detailEntity.getMealName();
        this.mealKcal = detailEntity.getMealKcal();
        this.mealCount = detailEntity.getMealCount();
    }
}