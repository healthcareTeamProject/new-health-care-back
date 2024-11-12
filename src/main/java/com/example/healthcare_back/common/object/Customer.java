package com.example.healthcare_back.common.object;

import java.math.BigDecimal;

import com.example.healthcare_back.entity.customer.CustomerEntity;

import lombok.Getter;

// 고객 정보를 나타내는 클래스

@Getter
public class Customer {
    private String userId;
    private String name;
    private String nickname;
    private String telNumber;
    private String profileImage;
    private String personalGoals;
    private BigDecimal height;

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