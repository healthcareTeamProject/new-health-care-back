package com.example.healthcare_back.entity;

import com.example.healthcare_back.dto.request.auth.SignUpRequestDto;
// import com.example.healthcare_back.dto.request.customer.PostUserMuscleFatRequestDto;
// import com.example.healthcare_back.dto.request.customer.PostUserThreeMajorLiftRequestDto;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
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
    @Positive
    @Column(precision = 5, scale = 1)
    private BigDecimal height;


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
}
