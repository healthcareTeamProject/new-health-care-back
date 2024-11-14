package com.example.healthcare_back.common.object;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.healthcare_back.common.util.CustomBigDecimalSerializer;
import com.example.healthcare_back.dto.response.schedule.GetMealDetailResponseDto;
import com.example.healthcare_back.entity.schedule.MealScheduleDetailEntity;
import com.example.healthcare_back.entity.schedule.MealScheduleEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;

@Getter
public class MealScheduleList {

    private Integer mealScheduleNumber;
    private String mealTitle;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime mealScheduleStart;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime mealScheduleEnd;
    private List<GetMealDetailResponseDto> mealMemo; // mealDetails 대신 mealMemo로 변경
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal totalKcal;  // 전체 칼로리 합계 필드 추가

    // MealScheduleEntity와 List<MealScheduleDetailEntity>를 매개변수로 받는 생성자
    public MealScheduleList(MealScheduleEntity mealScheduleEntity, List<MealScheduleDetailEntity> detailEntities) {
        this.mealScheduleNumber = mealScheduleEntity.getMealScheduleNumber();
        this.mealTitle = mealScheduleEntity.getMealTitle();
        this.mealScheduleStart = mealScheduleEntity.getMealScheduleStart();
        this.mealScheduleEnd = mealScheduleEntity.getMealScheduleEnd();

        // MealDetailResponse 리스트로 변환하여 mealMemo에 저장
        this.mealMemo = detailEntities.stream()
            .map(GetMealDetailResponseDto::new)
            .collect(Collectors.toList());

        // 총 칼로리 계산 (mealKcal * mealCount)
        this.totalKcal = detailEntities.stream()
        .map(detail -> detail.getMealKcal().multiply(BigDecimal.valueOf(detail.getMealCount())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
