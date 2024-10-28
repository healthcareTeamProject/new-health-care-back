package com.example.healthcare_back.common.object;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.UserThreeMajorLiftEntity;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class UserThreeMajorLiftList {
    
    private Integer threeMajorLiftNumber;
    private String userId;
    private BigDecimal deadlift;
    private BigDecimal benchPress;
    private BigDecimal squat;
    private LocalDateTime threeMajorLiftDate;

    // UserThreeMajorLiftEntity 객체를 받아 UserThreeMajorLiftList 객체를 초기화하는 생성자
    public UserThreeMajorLiftList(UserThreeMajorLiftEntity userThreeMajorLiftEntity) {
        // userThreeMajorLiftEntity가 null인 경우 예외를 발생시킴
        if (userThreeMajorLiftEntity == null) {
            throw new IllegalArgumentException("UserThreeMajorLiftEntity cannot be null");
        }
        // UserThreeMajorLiftEntity에서 3대 중량 기록 번호를 가져옴
        this.threeMajorLiftNumber = userThreeMajorLiftEntity.getThreeMajorLiftNumber();
        // CustomerEntity에서 사용자 ID를 가져옴. null인 경우 null 설정
        this.userId = userThreeMajorLiftEntity.getCustomerEntity() != null 
            ? userThreeMajorLiftEntity.getCustomerEntity().getUserId() 
            : null;
        // UserThreeMajorLiftEntity에서 데드리프트, 벤치프레스, 스쿼트 중량을 가져옴
        this.deadlift = userThreeMajorLiftEntity.getDeadlift();
        this.benchPress = userThreeMajorLiftEntity.getBenchPress();
        this.squat = userThreeMajorLiftEntity.getSquat();
        // 3대 중량 기록 날짜를 가져옴
        this.threeMajorLiftDate = userThreeMajorLiftEntity.getThreeMajorLiftDate();
    }

    // UserThreeMajorLiftEntity 리스트를 받아 UserThreeMajorLiftList 리스트로 변환하는 정적 메서드
    public static List<UserThreeMajorLiftList> getList(List<UserThreeMajorLiftEntity> userThreeMajorLiftEntities) {
        List<UserThreeMajorLiftList> threeMajorLift = new ArrayList<>();
        // 각 UserThreeMajorLiftEntity에 대해 UserThreeMajorLiftList로 변환하여 추가
        for (UserThreeMajorLiftEntity entity : userThreeMajorLiftEntities) {
            threeMajorLift.add(new UserThreeMajorLiftList(entity));
        }
        return threeMajorLift; // 변환된 리스트 반환
    }
}

