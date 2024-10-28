package com.example.healthcare_back.entity;

import com.example.healthcare_back.dto.request.auth.SignUpRequestDto;
import com.example.healthcare_back.dto.request.customer.PostUserMuscleFatRequestDto;
import com.example.healthcare_back.dto.request.customer.PostUserThreeMajorLiftRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
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
    private String personalGoals;
    @Column(precision = 5, scale = 1)
    private Double height;
    @Column(precision = 5, scale = 1)
    private Double weight;
    @Column(precision = 5, scale = 1)
    private Double skeletalMuscleMass;
    @Column(precision = 5, scale = 1)
    private Double bodyFatMass;
    @Column(precision = 5, scale = 1)
    private Double deadlift;
    @Column(precision = 5, scale = 1)
    private Double benchPress;
    @Column(precision = 5, scale = 1)
    private Double squat;


    public CustomerEntity(SignUpRequestDto dto) {
        this.userId = dto.getUserId();
        this.name = dto.getName();
        this.nickname = dto.getNickname();
        this.password = dto.getPassword();
        this.telNumber = dto.getTelNumber();
        this.joinPath = dto.getJoinPath();
        this.snsId = dto.getSnsId();
        this.profileImage = dto.getProfileImage();
        this.personalGoals = dto.getPersonalGoals();
        this.height = dto.getHeight();
        
    }

    public CustomerEntity(@Valid PostUserMuscleFatRequestDto dto) {
        this.weight = dto.getWeight();
        this.skeletalMuscleMass = dto.getSkeletalMuscleMass();
        this.bodyFatMass = dto.getBodyFatMass();
    }

    public CustomerEntity(@Valid PostUserThreeMajorLiftRequestDto dto) {
        this.deadlift = dto.getDeadlift();
        this.benchPress = dto.getBenchPress();
        this.squat = dto.getSquat();
    }
}
