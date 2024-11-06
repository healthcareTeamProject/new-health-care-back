package com.example.healthcare_back.dto.response.schedule;

import java.time.LocalDateTime;

import com.example.healthcare_back.entity.schedule.HealthScheduleEntity;

import lombok.Getter;

@Getter
public class GetHealthScheduleResponseDto {
    
    private Integer healthScheduleNumber;
    private String userId;
    private String healthTitle;
    private String healthMemo;
    private LocalDateTime healthScheduleStart;
    private LocalDateTime healthScheduleEnd;
    
    public GetHealthScheduleResponseDto(HealthScheduleEntity healthScheduleEntity) {
        this.healthScheduleNumber = healthScheduleEntity.getHealthScheduleNumber();
        this.userId = healthScheduleEntity.getUserId();
        this.healthTitle = healthScheduleEntity.getHealthTitle();
        this.healthMemo = healthScheduleEntity.getHealthMemo();
        this.healthScheduleStart = healthScheduleEntity.getHealthScheduleStart();
        this.healthScheduleEnd = healthScheduleEntity.getHealthScheduleEnd();
    }
}
