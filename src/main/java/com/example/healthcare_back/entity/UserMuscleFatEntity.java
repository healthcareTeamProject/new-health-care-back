package com.example.healthcare_back.entity;

import com.example.healthcare_back.dto.request.auth.SignUpRequestDto;
import com.example.healthcare_back.dto.request.auth.SignUpUserMuscleFatRequestDto;
import com.example.healthcare_back.dto.request.customer.PatchUserMuscleFatRequestDto;
import com.example.healthcare_back.dto.request.customer.PostUserMuscleFatRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 회원 신체정보 엔터티

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="userMuscleFat")
@Table(name="user_muscle_fat")
public class UserMuscleFatEntity {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userMuscleFatNumber;

    @Column(nullable = false)
    private String userId;

    @NotNull 
    @Column(precision = 5, scale = 1)
    private BigDecimal weight;

    @Column(precision = 5, scale = 1)
    private BigDecimal skeletalMuscleMass;

    @Column(precision = 5, scale = 1)
    private BigDecimal bodyFatMass;

    @Column(updatable = false)
    private LocalDateTime userMuscleFatDate;

    @PrePersist
    protected void onCreate() {
        this.userMuscleFatDate = LocalDateTime.now(); 
        userMuscleFatDate = LocalDateTime.now(); // 현재 시간으로 설정
    }
    
    public UserMuscleFatEntity(SignUpRequestDto dto) {
        this.userId = dto.getUserId();
        this.weight = dto.getWeight();
        this.skeletalMuscleMass = dto.getSkeletalMuscleMass();
        this.bodyFatMass = dto.getBodyFatMass();
    }

    public UserMuscleFatEntity(SignUpUserMuscleFatRequestDto dto) {
        this.userId = dto.getUserId();
        this.weight = dto.getWeight();
        this.skeletalMuscleMass = dto.getSkeletalMuscleMass();
        this.bodyFatMass = dto.getBodyFatMass();
    }

    public UserMuscleFatEntity(PostUserMuscleFatRequestDto dto) {
        this.weight = dto.getWeight();
        this.skeletalMuscleMass = dto.getSkeletalMuscleMass();
        this.bodyFatMass = dto.getBodyFatMass();
    }

    public UserMuscleFatEntity(PatchUserMuscleFatRequestDto dto) {
        this.weight = dto.getWeight();
        this.skeletalMuscleMass = dto.getSkeletalMuscleMass();
        this.bodyFatMass = dto.getBodyFatMass();
    }

}