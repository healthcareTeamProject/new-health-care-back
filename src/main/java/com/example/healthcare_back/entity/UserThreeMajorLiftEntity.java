package com.example.healthcare_back.entity;

import com.example.healthcare_back.dto.request.auth.SignUpRequestDto;
import com.example.healthcare_back.dto.request.customer.PostUserThreeMajorLiftRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 회원 3대측정 기록 엔터티

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="userThreeMajorLift")
@Table(name="user_three_major_lift")
public class UserThreeMajorLiftEntity {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userThreeMajorLiftNumber;

    @Column(nullable = false)
    private String userId;

    @Column(precision = 5, scale = 1)
    private BigDecimal deadlift;

    @Column(precision = 5, scale = 1)
    private BigDecimal benchPress;


    @Column(precision = 5, scale = 1)
    private BigDecimal squat;

    @Column(updatable = false)
    private LocalDateTime userThreeMajorLiftDate;

    @PrePersist
    protected void onCreate() {
        this.userThreeMajorLiftDate = LocalDateTime.now(); 
        userThreeMajorLiftDate = LocalDateTime.now(); // 현재 시간으로 설정
    }

    public UserThreeMajorLiftEntity(SignUpRequestDto dto) {
        this.userId = dto.getUserId();
        this.deadlift = dto.getDeadlift();
        this.benchPress = dto.getBenchPress();
        this.squat = dto.getSquat();
    }

    public UserThreeMajorLiftEntity(PostUserThreeMajorLiftRequestDto dto) {
        this.deadlift = dto.getDeadlift();
        this.benchPress = dto.getBenchPress();
        this.squat = dto.getSquat();
    }
    
}