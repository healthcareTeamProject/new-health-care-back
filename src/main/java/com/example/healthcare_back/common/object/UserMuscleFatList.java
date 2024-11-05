package com.example.healthcare_back.common.object;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.customer.UserMuscleFatEntity;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class UserMuscleFatList {
    private final Integer userMuscleFatNumber;
    private final String userId;
    private final BigDecimal weight;
    private final BigDecimal skeletalMuscleMass;
    private final BigDecimal bodyFatMass;
    private final LocalDateTime userMuscleFatDate;

    public UserMuscleFatList(UserMuscleFatEntity userMuscleFatEntity) {
        this.userMuscleFatNumber = userMuscleFatEntity.getUserMuscleFatNumber();
        this.userId = userMuscleFatEntity.getUserId();
        this.weight = userMuscleFatEntity.getWeight();
        this.skeletalMuscleMass = userMuscleFatEntity.getSkeletalMuscleMass();
        this.bodyFatMass = userMuscleFatEntity.getBodyFatMass();
        this.userMuscleFatDate = userMuscleFatEntity.getUserMuscleFatDate();
    }

    public static List<UserMuscleFatList> getList(List<UserMuscleFatEntity> userMuscleFatEntities) {
        List<UserMuscleFatList> MuscleFatList = new ArrayList<>();
        for (UserMuscleFatEntity entity : userMuscleFatEntities) {
            MuscleFatList.add(new UserMuscleFatList(entity));
        }
        return MuscleFatList; 
    }
}
