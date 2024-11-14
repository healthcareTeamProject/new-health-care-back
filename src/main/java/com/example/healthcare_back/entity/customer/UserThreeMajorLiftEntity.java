package com.example.healthcare_back.entity.customer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.healthcare_back.common.util.CustomBigDecimalSerializer;
import com.example.healthcare_back.dto.request.auth.SignUpRequestDto;
import com.example.healthcare_back.dto.request.customer.PatchUserThreeMajorLiftRequestDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal deadlift;
    @Column(precision = 5, scale = 1)
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal benchPress;
    @Column(precision = 5, scale = 1)
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal squat;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime userThreeMajorLiftDate;

    public UserThreeMajorLiftEntity(SignUpRequestDto dto) {
        this.userId = dto.getUserId();
        this.deadlift = dto.getDeadlift();
        this.benchPress = dto.getBenchPress();
        this.squat = dto.getSquat();
    }

    public UserThreeMajorLiftEntity(PatchUserThreeMajorLiftRequestDto dto) {
        this.deadlift = dto.getDeadlift();
        this.benchPress = dto.getBenchPress();
        this.squat = dto.getSquat();
    }
    
}