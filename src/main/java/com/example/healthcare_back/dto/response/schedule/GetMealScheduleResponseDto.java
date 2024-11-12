package com.example.healthcare_back.dto.response.schedule;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.schedule.MealScheduleDetailEntity;
import com.example.healthcare_back.entity.schedule.MealScheduleEntity;

import lombok.Getter;

@Getter
public class GetMealScheduleResponseDto extends ResponseDto {
    
    private Integer mealScheduleNumber;
    private String mealTitle;
    private LocalDateTime mealScheduleStart;
    private LocalDateTime mealScheduleEnd;
    private List<GetMealDetailResponseDto> mealMemo;  // mealMemo를 List<GetMealDetailResponseDto> 타입으로 유지
    private List<BigDecimal> totalKcal;  // 각 항목별 칼로리 리스트로 변경

    public GetMealScheduleResponseDto(MealScheduleEntity mealSchedule, List<MealScheduleDetailEntity> detailEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.mealScheduleNumber = mealSchedule.getMealScheduleNumber();
        this.mealTitle = mealSchedule.getMealTitle();
        this.mealScheduleStart = mealSchedule.getMealScheduleStart();
        this.mealScheduleEnd = mealSchedule.getMealScheduleEnd();

        // mealMemo를 문자열이 아닌 세부 정보 리스트로 설정
        this.mealMemo = detailEntities.stream()
            .map(GetMealDetailResponseDto::new)
            .collect(Collectors.toList());

        // 각 항목의 칼로리를 리스트로 저장
        this.totalKcal = detailEntities.stream()
            .map(MealScheduleDetailEntity::getMealKcal)  // 각 MealScheduleDetailEntity에서 getMealKcal 호출
            .collect(Collectors.toList());  // 리스트로 수집
    }

    // 수정된 구조의 성공 응답 메서드
    public static ResponseEntity<GetMealScheduleResponseDto> success(MealScheduleEntity mealSchedule, List<MealScheduleDetailEntity> details) {
        GetMealScheduleResponseDto responseBody = new GetMealScheduleResponseDto(mealSchedule, details);
        return ResponseEntity.ok(responseBody);
    }
}