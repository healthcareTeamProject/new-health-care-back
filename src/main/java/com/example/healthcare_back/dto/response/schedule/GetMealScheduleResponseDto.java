package com.example.healthcare_back.dto.response.schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.healthcare_back.entity.schedule.MealScheduleDetailEntity;
import com.example.healthcare_back.entity.schedule.MealScheduleEntity;

import lombok.Getter;

@Getter
public class GetMealScheduleResponseDto {

    private Integer mealScheduleNumber; // 식단 일정 번호
    private String userId; // 사용자 ID
    private LocalDateTime mealScheduleDate; // 식단 일정 날짜
    private LocalDateTime mealScheduleStart; // 시작 시간
    private LocalDateTime mealScheduleEnd; // 종료 시간
    private List<GetMealScheduleDetailResponseDto> mealDetails; // 식단 세부사항

    public GetMealScheduleResponseDto(MealScheduleEntity mealScheduleEntity, List<MealScheduleDetailEntity> mealDetails) {
        this.mealScheduleNumber = mealScheduleEntity.getMealScheduleNumber();
        this.userId = mealScheduleEntity.getUserId();
        this.mealScheduleStart = mealScheduleEntity.getMealScheduleStart(); 
        this.mealScheduleEnd = mealScheduleEntity.getMealScheduleEnd(); 
        this.mealDetails = mealDetails.stream()
                                      .map(GetMealScheduleDetailResponseDto::new)
                                      .collect(Collectors.toList());
    }
}
