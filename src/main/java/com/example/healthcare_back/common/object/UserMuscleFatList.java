package com.example.healthcare_back.common.object;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.UserMuscleFatEntity;


import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class UserMuscleFatList {

    private Integer userMuscleFatNumber;
    private String userId;
    private BigDecimal weight;
    private BigDecimal skeletalMuscleMass;
    private BigDecimal bodyFatMass;
    private LocalDateTime userMuscleFatDate;

    // UserMuscleFatEntity 객체를 받아 UserMuscleFatList 객체를 초기화하는 생성자
    public UserMuscleFatList(UserMuscleFatEntity userMuscleFatEntity) {
        // userMuscleFatEntity가 null인 경우 예외를 발생시킴
        if (userMuscleFatEntity == null) {
            throw new IllegalArgumentException("UserMuscleFatEntity cannot be null");
        }
        // UserMuscleFatEntity에서 데이터 번호를 가져옴
        this.userMuscleFatNumber = userMuscleFatEntity.getUserMuscleFatNumber(); // 번호 추가
        // CustomerEntity에서 사용자 ID를 가져옴. null인 경우 null 설정
        this.userId = userMuscleFatEntity.getCustomerEntity() != null 
            ? userMuscleFatEntity.getCustomerEntity().getUserId() 
            : null;
        // UserMuscleFatEntity에서 체중, 골격근량, 체지방량, 기록 날짜를 가져옴
        this.weight = userMuscleFatEntity.getWeight();
        this.skeletalMuscleMass = userMuscleFatEntity.getSkeletalMuscleMass();
        this.bodyFatMass = userMuscleFatEntity.getBodyFatMass();
        this.userMuscleFatDate = userMuscleFatEntity.getUserMuscleFatDate();
    }

    // UserMuscleFatEntity 리스트를 받아 UserMuscleFatList 리스트로 변환하는 정적 메서드
    public static List<UserMuscleFatList> getList(List<UserMuscleFatEntity> userMuscleFatEntities) {
        List<UserMuscleFatList> userMuscleFat = new ArrayList<>();
        // 각 UserMuscleFatEntity에 대해 UserMuscleFatList로 변환하여 추가
        for (UserMuscleFatEntity entity : userMuscleFatEntities) {
            userMuscleFat.add(new UserMuscleFatList(entity));
        }
        return userMuscleFat; // 변환된 리스트 반환
    }
}

