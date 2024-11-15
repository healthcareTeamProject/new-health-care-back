package com.example.healthcare_back.dto.response.schedule;

import java.math.BigDecimal;

import com.example.healthcare_back.common.util.CustomBigDecimalSerializer;
import com.example.healthcare_back.entity.schedule.MealScheduleDetailEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;

@Getter
public class GetMealDetailResponseDto {

    private final Integer mealScheduleDetailNumber;
    private final String mealName;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private final BigDecimal mealKcal;
    private final Integer mealCount;

    public GetMealDetailResponseDto(MealScheduleDetailEntity detailEntity) {
        this.mealScheduleDetailNumber = detailEntity.getMealScheduleDetailNumber();
        this.mealName = detailEntity.getMealName();
        this.mealKcal = detailEntity.getMealKcal();
        this.mealCount = detailEntity.getMealCount();
    }
}