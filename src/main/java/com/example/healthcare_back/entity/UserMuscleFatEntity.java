package com.example.healthcare_back.entity;

import com.example.healthcare_back.dto.request.auth.SignUpRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="userMuscleFat")
@Table(name="user_muscle_fat")
public class UserMuscleFatEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userMuscleFatNumber;
    private String userId;
    @NotNull @Column(precision = 5, scale = 0)
    private Double weight;
    @Column(precision = 5, scale = 1)
    private Double skeletalMuscleMass;
    @Column(precision = 5, scale = 1)
    private Double bodyFatMass;
    // private LocalDate userMuscleFatDate;

    @Column(updatable = false)
    private LocalDateTime userMuscleFatDate;
    // @Column(nullable = false)
    // private LocalDateTime updatedAt;

    public UserMuscleFatEntity(SignUpRequestDto dto) {

        this.userId = dto.getUserId();
        // this.skeletalMuscleMass = dto.getSkeletalMuscleMass();
        // this.bodyFatMass = dto.getBodyFatMass();
        this.userMuscleFatDate = LocalDateTime.now(); 

    }

    @PrePersist
    protected void onCreate() {
        userMuscleFatDate = LocalDateTime.now(); // 현재 시간으로 설정
        // updatedAt = LocalDateTime.now(); // 현재 시간으로 설정
    }

    // @PreUpdate
    // protected void onUpdate() {
    //     updatedAt = LocalDateTime.now(); // 현재 시간으로 업데이트
    // }
}
