package com.example.healthcare_back.common.object;

import com.example.healthcare_back.entity.CustomerEntity;

import lombok.Getter;

/**
 * 고객 정보를 나타내는 클래스입니다.
 */
@Getter
public class Customer {

    private final String userId;
    private final String name;
    private final String nickname;
    private final String telNumber;
    private final String profileImage;
    private final String personalGoals;
    private final Double height;
  

    private Customer(CustomerEntity customerEntity) {
        this.userId = customerEntity.getUserId();
        this.name = customerEntity.getName();
        this.nickname = customerEntity.getNickname();
        this.telNumber = customerEntity.getTelNumber();
        this.profileImage = customerEntity.getProfileImage();
        this.personalGoals = customerEntity.getPersonalGoals();
        this.height = customerEntity.getHeight();
    }

}