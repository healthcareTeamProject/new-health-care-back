package com.example.healthcare_back.common.object;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.customer.UserMuscleFatEntity;

import lombok.Getter;

@Getter
public class UserMuscleFatList {
    private Integer userMuscleFatNumber;
    private String userId;
    private BigDecimal weight;
    private BigDecimal skeletalMuscleMass;
    private BigDecimal bodyFatMass;
    private LocalDateTime userMuscleFatDate;

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
