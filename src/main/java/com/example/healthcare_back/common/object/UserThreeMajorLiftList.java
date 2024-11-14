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
    
    private Integer userThreeMajorLiftNumber;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal deadlift;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal benchPress;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal squat;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime userThreeMajorLiftDate;

    // UserThreeMajorLiftEntity 객체를 사용하여 UserThreeMajorLiftList 객체를 생성하는 생성자
    public UserThreeMajorLiftList(UserThreeMajorLiftEntity userThreeMajorLiftEntity) {
        this.userThreeMajorLiftNumber = userThreeMajorLiftEntity.getUserThreeMajorLiftNumber();
        this.deadlift = userThreeMajorLiftEntity.getDeadlift();
        this.benchPress = userThreeMajorLiftEntity.getBenchPress();
        this.squat = userThreeMajorLiftEntity.getSquat();
        this.userThreeMajorLiftDate = userThreeMajorLiftEntity.getUserThreeMajorLiftDate();
    }

    // UserThreeMajorLiftEntity 목록을 받아 UserThreeMajorLiftList 목록으로 변환하여 반환하는 메서드
    public static List<UserThreeMajorLiftList> getList(List<UserThreeMajorLiftEntity> userThreeMajorLiftEntities) {
        List<UserThreeMajorLiftList> userThreeMajorLiftList = new ArrayList<>();
        for (UserThreeMajorLiftEntity entity : userThreeMajorLiftEntities) {
            userThreeMajorLiftList.add(new UserThreeMajorLiftList(entity));
        }
        return userThreeMajorLiftList;
    }
}
