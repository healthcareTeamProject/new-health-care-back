package com.example.healthcare_back.common.object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.schedule.HealthScheduleEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class HealthScheduleList {

    private Integer healthScheduleNumber;
    private String healthTitle;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime healthScheduleStart;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime healthScheduleEnd;

    // HealthScheduleEntity 객체를 이용하여 HealthScheduleList 객체를 생성하는 생성자
    public HealthScheduleList(HealthScheduleEntity healthScheduleEntity) {
        this.healthScheduleNumber = healthScheduleEntity.getHealthScheduleNumber();
        this.healthTitle = healthScheduleEntity.getHealthTitle();
        this.healthScheduleStart = healthScheduleEntity.getHealthScheduleStart();
        this.healthScheduleEnd = healthScheduleEntity.getHealthScheduleEnd();
    }
    
    // HealthScheduleEntity 목록을 받아 HealthScheduleList 목록으로 변환하여 반환하는 메서드
    public static List<HealthScheduleList> getList(List<HealthScheduleEntity> healthScheduleEntities) {
        List<HealthScheduleList> healthScheduleLists = new ArrayList<>();
        for (HealthScheduleEntity entity : healthScheduleEntities) {
            healthScheduleLists.add(new HealthScheduleList(entity));
        }
        return healthScheduleLists; 
    }
    
}
