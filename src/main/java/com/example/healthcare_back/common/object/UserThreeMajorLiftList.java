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

    public UserThreeMajorLiftList(UserThreeMajorLiftEntity userThreeMajorLiftEntity) {
        this.threeMajorLiftNumber = userThreeMajorLiftEntity.getThreeMajorLiftNumber();
        this.deadlift = userThreeMajorLiftEntity.getDeadlift();
        this.benchPress = userThreeMajorLiftEntity.getBenchPress();
        this.squat = userThreeMajorLiftEntity.getSquat();
        this.threeMajorLiftDate = userThreeMajorLiftEntity.getThreeMajorLiftDate();
    }

    public static List<UserThreeMajorLiftList> getList(List<UserThreeMajorLiftEntity> userThreeMajorLiftEntities) {
        List<UserThreeMajorLiftList> threeMajorLiftList = new ArrayList<>();
        for (UserThreeMajorLiftEntity entity : userThreeMajorLiftEntities) {
            threeMajorLiftList.add(new UserThreeMajorLiftList(entity));
        }
        return threeMajorLiftList;
    }
}

