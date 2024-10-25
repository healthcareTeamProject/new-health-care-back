package com.example.healthcare_back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.UserMuscleFatEntity;


import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class UserMuscleFatList {

    private Integer userMuscleFatNumber = 1;
    private String userId;
    private Double weight;
    private Double skeletalMuscleMass;
    private Double bodyFatMass;
    private LocalDateTime userMuscleFatDate;


    public UserMuscleFatList (UserMuscleFatEntity userMuscleFatEntity) {

        this.userId = userMuscleFatEntity.getUserId();
        this.weight = userMuscleFatEntity.getWeight();
        this.skeletalMuscleMass = userMuscleFatEntity.getSkeletalMuscleMass();
        this.bodyFatMass = userMuscleFatEntity.getBodyFatMass();
        this.userMuscleFatDate = userMuscleFatEntity.getUserMuscleFatDate();

    }

    public static List<UserMuscleFatList> getList(List<UserMuscleFatEntity> userMuscleFatEntities) {

        List<UserMuscleFatList> muscleFat = new ArrayList<>();
        for (UserMuscleFatEntity userMuscleFatEntity: userMuscleFatEntities) {
            UserMuscleFatList userMuscleFatList = new UserMuscleFatList(userMuscleFatEntity);
            muscleFat.add(userMuscleFatList);
        }
        return muscleFat;

    }
}
