package com.example.healthcare_back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.UserThreeMajorLiftEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class UserThreeMajorLiftList {
    
    private Integer threeMajorLiftNumber = 1;
    private String userId;
    private Double deadlift;
    private Double benchPress;
    private Double squat;
    private LocalDateTime threeMajorLiftDate;

    public UserThreeMajorLiftList (UserThreeMajorLiftEntity userThreeMajorLiftEntity) {

        this.userId = userThreeMajorLiftEntity.getUserId();
        this.deadlift = userThreeMajorLiftEntity.getDeadlift();
        this.benchPress = userThreeMajorLiftEntity.getBenchPress();
        this.squat = userThreeMajorLiftEntity.getSquat();
        this.threeMajorLiftDate = userThreeMajorLiftEntity.getThreeMajorLiftDate();

    }

    public static List<UserThreeMajorLiftList> getList(List<UserThreeMajorLiftEntity> userThreeMajorLiftEntities) {

        List<UserThreeMajorLiftList> threeMajorLift = new ArrayList<>();
        for (UserThreeMajorLiftEntity userThreeMajorLiftEntity: userThreeMajorLiftEntities) {
            UserThreeMajorLiftList userThreeMajorLiftList = new UserThreeMajorLiftList(userThreeMajorLiftEntity);
            threeMajorLift.add(userThreeMajorLiftList);
        }
        return threeMajorLift;

    }
}
