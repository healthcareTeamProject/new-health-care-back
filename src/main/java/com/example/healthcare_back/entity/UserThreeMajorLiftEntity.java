package com.example.healthcare_back.entity;

import com.example.healthcare_back.dto.request.auth.SignUpRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="userThreeMajorLift")
@Table(name="user-three-major-lift")
public class UserThreeMajorLiftEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer threeMajorLiftNumber;
    private String userId;
    @Column(precision = 5, scale = 0)
    private Double deadlift;
    @Column(precision = 5, scale = 0)
    private Double benchPress;
    @Column(precision = 5, scale = 0)
    private Double squat;
    // private LocalDate threeMajorLiftsDate;

    @Column(updatable = false)
    private LocalDateTime threeMajorLiftDate;
    // @Column(nullable = false)
    // private LocalDateTime updatedAt;


    public UserThreeMajorLiftEntity(SignUpRequestDto dto) {

        this.userId = dto.getUserId();
        // this.deadlift = dto.getDeadlift();
        // this.benchPress = dto.getBenchPress();
        // this.squat = dto.getSquat();
        this.threeMajorLiftDate = LocalDateTime.now(); 

    }

    @PrePersist
    protected void onCreate() {
        threeMajorLiftDate = LocalDateTime.now(); // 현재 시간으로 설정
        // updatedAt = LocalDateTime.now(); // 현재 시간으로 설정
    }

    // @PreUpdate
    // protected void onUpdate() {
    //     updatedAt = LocalDateTime.now(); // 현재 시간으로 업데이트
    // }

}
