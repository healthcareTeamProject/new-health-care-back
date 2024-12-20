package com.example.healthcare_back.entity.customer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.healthcare_back.common.util.CustomBigDecimalSerializer;
import com.example.healthcare_back.dto.request.auth.SignUpRequestDto;
import com.example.healthcare_back.dto.request.customer.PatchUserMuscleFatRequestDto;
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
    @Column(precision = 5, scale = 1)
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal weight;
    @Column(precision = 5, scale = 1)
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal skeletalMuscleMass;
    @Column(precision = 5, scale = 1)
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal bodyFatMass;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime userMuscleFatDate;
    
    public UserMuscleFatEntity(SignUpRequestDto dto) {
        this.userId = dto.getUserId();
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