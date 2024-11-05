package com.example.healthcare_back.dto.request.schedule;

import java.time.LocalDateTime;
import java.util.List;

import com.example.healthcare_back.dto.response.schedule.GetMealScheduleDetailResponseDto;

import lombok.Getter;

@Getter
public class DeleteMealScheduleRequestDto {

    private String userId;               // 유저 ID
    private String mealTitle;            // 식사 종류 (아침, 점심, 저녁)
    private LocalDateTime mealScheduleStart;   // 스케줄 시작일
    private LocalDateTime mealScheduleEnd;     // 스케줄 종료일
    
    private List<GetMealScheduleDetailResponseDto> mealDetails;  // 세부 항목 (식품명, 칼로리, 개수)
    
}
