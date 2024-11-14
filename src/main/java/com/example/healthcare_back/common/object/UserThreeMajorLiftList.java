package com.example.healthcare_back.common.object;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.common.util.CustomBigDecimalSerializer;
import com.example.healthcare_back.entity.customer.UserThreeMajorLiftEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
@Getter
public class UserThreeMajorLiftList {
    
    private final Integer userThreeMajorLiftNumber;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private final BigDecimal deadlift;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private final BigDecimal benchPress;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private final BigDecimal squat;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDateTime userThreeMajorLiftDate;

    public UserThreeMajorLiftList(UserThreeMajorLiftEntity userThreeMajorLiftEntity) {
        this.userThreeMajorLiftNumber = userThreeMajorLiftEntity.getUserThreeMajorLiftNumber();
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