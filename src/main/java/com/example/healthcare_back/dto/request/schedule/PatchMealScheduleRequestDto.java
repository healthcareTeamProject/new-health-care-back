package com.example.healthcare_back.dto.request.schedule;
import java.util.List;

import com.example.healthcare_back.dto.response.schedule.GetMealScheduleDetailResponseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchMealScheduleRequestDto {
    
    private String userId;               // 유저 ID
    private String mealTitle;            // 식사 종류 (아침, 점심, 저녁)
    private String memo;
    private List<GetMealScheduleDetailResponseDto> mealDetails;  // 세부 항목 (식품명, 칼로리, 개수)

}
