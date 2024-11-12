package com.example.healthcare_back.common.object;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.customer.UserThreeMajorLiftEntity;

import lombok.Getter;

@Getter
public class UserThreeMajorLiftList {
    
    private Integer userThreeMajorLiftNumber;
    private String userId;
    private BigDecimal deadlift;
    private BigDecimal benchPress;
    private BigDecimal squat;
    private LocalDateTime userThreeMajorLiftDate;

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
