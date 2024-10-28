package com.example.healthcare_back.entity;

import com.example.healthcare_back.dto.request.customer.PostUserMuscleFatRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private CustomerEntity customerEntity;
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userMuscleFatNumber;
    @NotBlank
    @Column(name = "user_id", insertable = false, updatable = false) // DB와의 충돌 방지를 위해 설정
    private String userId;
    @NotNull 
    @Positive
    @Column(precision = 5, scale = 1)
    private BigDecimal weight;
    @Positive
    @Column(precision = 5, scale = 1)
    private BigDecimal skeletalMuscleMass;
    @Positive
    @Column(precision = 5, scale = 1)
    private BigDecimal bodyFatMass;

    @Column(updatable = false)
    private LocalDateTime userMuscleFatDate;
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.userMuscleFatDate = LocalDateTime.now(); 
        userMuscleFatDate = LocalDateTime.now(); // 현재 시간으로 설정
        updatedAt = LocalDateTime.now(); // 현재 시간으로 설정
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now(); // 현재 시간으로 업데이트
    }

    public UserMuscleFatEntity(PostUserMuscleFatRequestDto dto) {
        this.weight = dto.getWeight();
        this.skeletalMuscleMass = dto.getSkeletalMuscleMass();
        this.bodyFatMass = dto.getBodyFatMass();
    }

     // CustomerEntity 설정 메서드
     public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
        // customerEntity가 설정되면 userId를 자동으로 업데이트
        this.userId = customerEntity.getUserId(); // userId 업데이트
    }
}
