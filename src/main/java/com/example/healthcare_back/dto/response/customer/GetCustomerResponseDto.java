package com.example.healthcare_back.dto.response.customer;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.common.util.CustomBigDecimalSerializer;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.customer.CustomerEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;

// 회원 정보 불러오기 Response DTO

@Getter
public class GetCustomerResponseDto extends ResponseDto {

    private String userId;
    private String name;
    private String nickname;
    private String telNumber;
    private String profileImage;
    private String personalGoals;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal height;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal deadlift;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal benchPress;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal squat;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal weight;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal skeletalMuscleMass;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal bodyFatMass;

    public GetCustomerResponseDto(String code, String message, CustomerEntity customerEntity) {
        super(code, message);
        this.userId = customerEntity.getUserId();
        this.name = customerEntity.getName();
        this.nickname = customerEntity.getNickname();
        this.telNumber = customerEntity.getTelNumber();
        this.profileImage = customerEntity.getProfileImage();
        this.personalGoals = customerEntity.getPersonalGoals();
        this.height = customerEntity.getHeight();
        this.deadlift = customerEntity.getDeadlift(); 
        this.benchPress = customerEntity.getBenchPress(); 
        this.squat = customerEntity.getSquat(); 
        this.weight = customerEntity.getWeight(); 
        this.skeletalMuscleMass = customerEntity.getSkeletalMuscleMass(); 
        this.bodyFatMass = customerEntity.getBodyFatMass(); 
    }

    public static ResponseEntity<GetCustomerResponseDto> success(CustomerEntity customerEntity) {
        GetCustomerResponseDto responseBody = new GetCustomerResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, customerEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}