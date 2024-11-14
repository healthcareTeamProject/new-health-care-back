package com.example.healthcare_back.common.object;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.common.util.CustomBigDecimalSerializer;
import com.example.healthcare_back.entity.customer.UserMuscleFatEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;

@Getter
public class UserMuscleFatList {

    private final Integer userMuscleFatNumber;
    private final String userId;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private final BigDecimal weight;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private final BigDecimal skeletalMuscleMass;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private final BigDecimal bodyFatMass;
    @JsonFormat(pattern = "yyyy-MM-dd")
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