package com.example.healthcare_back.entity;

import com.example.healthcare_back.dto.request.auth.SignUpRequestDto;
import com.example.healthcare_back.dto.request.customer.PatchCustomerRequestDto;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 회원 엔터티

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="customer")
@Table(name="customer")
public class CustomerEntity {

    @Id 
    private String userId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String telNumber;
    @Column(nullable = false)
    private String joinPath;
    private String snsId;
    private String profileImage;
    private String personalGoal;
    @Column(precision = 5, scale = 1)
    private BigDecimal height;
    
    @Column(precision = 5, scale = 1)
    private BigDecimal deadlift;
    @Column(precision = 5, scale = 1)
    private BigDecimal benchPress;
    @Column(precision = 5, scale = 1)
    private BigDecimal squat;

    @Column(precision = 5, scale = 1)
    private BigDecimal weight;
    @Column(precision = 5, scale = 1)
    private BigDecimal skeletalMuscleMass;
    @Column(precision = 5, scale = 1)
    private BigDecimal bodyFatMass;

    public CustomerEntity(SignUpRequestDto dto) {
        this.userId = dto.getUserId();
        this.name = dto.getName();
        this.nickname = dto.getNickname();
        this.password = dto.getPassword();
        this.telNumber = dto.getTelNumber();
        this.joinPath = dto.getJoinPath();
        this.snsId = dto.getSnsId();
        this.profileImage = dto.getProfileImage();
        this.personalGoal = dto.getPersonalGoal();
        this.height = dto.getHeight(); 
        this.deadlift = dto.getDeadlift(); 
        this.benchPress = dto.getBenchPress(); 
        this.squat = dto.getSquat(); 
        this.weight = dto.getWeight(); 
        this.skeletalMuscleMass = dto.getSkeletalMuscleMass(); 
        this.bodyFatMass = dto.getBodyFatMass(); 
    }

    public void patchCustomer(PatchCustomerRequestDto dto) {
        this.name = dto.getName();
        this.nickname = dto.getNickname();
        this.profileImage = dto.getProfileImage();
        this.personalGoal = dto.getPersonalGoal();
        this.height = dto.getHeight();
    }


}