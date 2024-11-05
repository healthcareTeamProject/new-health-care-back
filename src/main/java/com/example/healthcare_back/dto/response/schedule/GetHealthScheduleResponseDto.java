package com.example.healthcare_back.dto.response.schedule;

import java.time.LocalDateTime;

import com.example.healthcare_back.entity.schedule.HealthScheduleEntity;

import lombok.Getter;

@Getter
public class GetHealthScheduleResponseDto {
    
    private Integer healthScheduleNumber;   // 운동 스케줄 번호
    private String userId;
    private String healthTitle;          // 운동 일정 제목
    private String healthMemo;           // 운동 일정 메모
    
    private LocalDateTime healthScheduleStart;  // 스케줄 시작일
    private LocalDateTime healthScheduleEnd;    // 스케줄 종료일
    
    public GetHealthScheduleResponseDto(HealthScheduleEntity healthScheduleEntity) {
        this.healthScheduleNumber = healthScheduleEntity.getHealthScheduleNumber();
        this.userId = healthScheduleEntity.getUserId();
        this.healthTitle = healthScheduleEntity.getHealthTitle();
        this.healthMemo = healthScheduleEntity.getHealthMemo();
        this.healthScheduleStart = healthScheduleEntity.getHealthScheduleStart();
        this.healthScheduleEnd = healthScheduleEntity.getHealthScheduleEnd();
    }
}
