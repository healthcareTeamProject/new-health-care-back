package com.example.healthcare_back.common.object;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.repository.resultSet.UserThreeMajorLiftResultSet;

import lombok.Getter;

@Getter
public class UserThreeMajorLiftList {
    
    private final Integer userThreeMajorLiftNumber;
    private final String userId = null;
    private final BigDecimal deadlift;
    private final BigDecimal benchPress;
    private final BigDecimal squat;
    private final String userThreeMajorLiftDate;

    public UserThreeMajorLiftList(UserThreeMajorLiftResultSet userThreeMajorLiftResultSet) {
        this.userThreeMajorLiftNumber = userThreeMajorLiftResultSet.getUserThreeMajorLiftNumber();
        this.deadlift = userThreeMajorLiftResultSet.getDeadlift();
        this.benchPress = userThreeMajorLiftResultSet.getBenchPress();
        this.squat = userThreeMajorLiftResultSet.getSquat();
        this.userThreeMajorLiftDate = userThreeMajorLiftResultSet.getUserThreeMajorLiftDate();
    }

    public static List<UserThreeMajorLiftList> getList(List<UserThreeMajorLiftResultSet> resultSets) {
        List<UserThreeMajorLiftList> userThreeMajorLift = new ArrayList<>();
        for (UserThreeMajorLiftResultSet resultSet: resultSets) {
            UserThreeMajorLiftList userThreeMajorLiftList = new UserThreeMajorLiftList(resultSet);
            userThreeMajorLift.add(userThreeMajorLiftList);
        }
        return userThreeMajorLift;
    }
}
