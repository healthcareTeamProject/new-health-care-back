package com.example.healthcare_back.common.object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.customer.UserMuscleFatEntity;
import com.example.healthcare_back.entity.schedule.HealthScheduleEntity;

import lombok.Getter;

@Getter
public class HealthScheduleList {

    private Integer healthScheduleNumber;
    private String userId;
    private String healthTitle;
    private LocalDateTime healthScheduleStart;
    private LocalDateTime healthScheduleEnd;

    public HealthScheduleList(HealthScheduleEntity healthScheduleEntity) {

        this.healthScheduleNumber = healthScheduleEntity.getHealthScheduleNumber();
        this.userId = healthScheduleEntity.getUserId();
        this.healthTitle = healthScheduleEntity.getHealthTitle();
        this.healthScheduleStart = healthScheduleEntity.getHealthScheduleStart();
        this.healthScheduleEnd = healthScheduleEntity.getHealthScheduleEnd();
    }
    
    public static List<HealthScheduleList> getList(List<HealthScheduleEntity> healthScheduleEntities) {
        List<HealthScheduleList> healthScheduleLists = new ArrayList<>();
        for (HealthScheduleEntity entity : healthScheduleEntities) {
            healthScheduleLists.add(new HealthScheduleList(entity));
        }
        return healthScheduleLists; 
    }
    
}
