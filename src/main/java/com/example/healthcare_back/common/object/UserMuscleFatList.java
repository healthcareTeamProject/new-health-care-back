package com.example.healthcare_back.common.object;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.common.util.CustomBigDecimalSerializer;
import com.example.healthcare_back.entity.customer.UserMuscleFatEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;

@Getter
public class UserMuscleFatList {
    private Integer userMuscleFatNumber;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal weight;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal skeletalMuscleMass;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal bodyFatMass;
    private LocalDateTime userMuscleFatDate;

    // UserMuscleFatEntity 객체를 사용하여 UserMuscleFatList 객체를 생성하는 생성자
    public UserMuscleFatList(UserMuscleFatEntity userMuscleFatEntity) {
        this.userMuscleFatNumber = userMuscleFatEntity.getUserMuscleFatNumber();
        this.weight = userMuscleFatEntity.getWeight();
        this.skeletalMuscleMass = userMuscleFatEntity.getSkeletalMuscleMass();
        this.bodyFatMass = userMuscleFatEntity.getBodyFatMass();
        this.userMuscleFatDate = userMuscleFatEntity.getUserMuscleFatDate();
    }

    // UserMuscleFatEntity 목록을 받아 UserMuscleFatList 목록으로 변환하여 반환하는 메서드
    public static List<UserMuscleFatList> getList(List<UserMuscleFatEntity> userMuscleFatEntities) {
        List<UserMuscleFatList> MuscleFatList = new ArrayList<>();
        for (UserMuscleFatEntity entity : userMuscleFatEntities) {
            MuscleFatList.add(new UserMuscleFatList(entity));
        }
        return MuscleFatList; 
    }
}
