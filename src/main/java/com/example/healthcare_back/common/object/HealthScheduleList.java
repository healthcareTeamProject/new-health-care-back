package com.example.healthcare_back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.schedule.HealthScheduleEntity;

import lombok.Getter;

@Getter
public class HealthScheduleList {

    private final Integer healthScheduleNumber;
    private final String healthTitle;
    private final String healthScheduleStart;
    private final String healthScheduleEnd;

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
