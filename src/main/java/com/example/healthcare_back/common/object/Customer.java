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

    /**
     * CustomerEntity를 기반으로 Customer 객체를 생성하는 팩토리 메서드입니다.
     *
     * @param customerEntity 생성할 Customer 객체의 정보가 포함된 CustomerEntity
     * @return Customer 객체
     */
    public static Customer fromEntity(CustomerEntity customerEntity) {
        return new Customer(customerEntity);
    }
}