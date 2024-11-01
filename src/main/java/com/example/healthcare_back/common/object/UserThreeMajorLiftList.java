package com.example.healthcare_back.common.object;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.UserThreeMajorLiftEntity;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class UserThreeMajorLiftList {
    
    private final Integer userThreeMajorLiftNumber;
    private final String userId;
    private final BigDecimal deadlift;
    private final BigDecimal benchPress;
    private final BigDecimal squat;
    private final LocalDateTime userThreeMajorLiftDate;

    public UserThreeMajorLiftList(UserThreeMajorLiftEntity userThreeMajorLiftEntity) {
        this.userThreeMajorLiftNumber = userThreeMajorLiftEntity.getUserThreeMajorLiftNumber();
        this.userId = userThreeMajorLiftEntity.getUserId();
        this.deadlift = userThreeMajorLiftEntity.getDeadlift();
        this.benchPress = userThreeMajorLiftEntity.getBenchPress();
        this.squat = userThreeMajorLiftEntity.getSquat();
        this.userThreeMajorLiftDate = userThreeMajorLiftEntity.getUserThreeMajorLiftDate();
    }

    public static List<UserThreeMajorLiftList> getList(List<UserThreeMajorLiftEntity> userThreeMajorLiftEntities) {
        List<UserThreeMajorLiftList> userThreeMajorLiftList = new ArrayList<>();
        for (UserThreeMajorLiftEntity entity : userThreeMajorLiftEntities) {
            userThreeMajorLiftList.add(new UserThreeMajorLiftList(entity));
        }
        return userThreeMajorLiftList;
    }
}
