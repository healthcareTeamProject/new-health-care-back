package com.example.healthcare_back.repository.resultSet;

import java.math.BigDecimal;

public interface ThreeMajorLiftResultSet {
    Integer getUserThreeMajorLiftNumber();
    String getUserId();
    BigDecimal getDeadlift();
    BigDecimal getBenchPress();
    BigDecimal getSquat();
    String getUserThreeMajorLiftDate();
}
