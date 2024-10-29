package com.example.healthcare_back.dto.response.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.UserMuscleFatEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class GetUserMuscleFatResponseDto extends ResponseDto {

    private Integer userMuscleFatNumber;
    private String userId;
    private BigDecimal weight;
    private BigDecimal skeletalMuscleMass;
    private BigDecimal bodyFatMass;
    private LocalDateTime userMuscleFatDate;


    public GetUserMuscleFatResponseDto(UserMuscleFatEntity userMuscleFatEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userMuscleFatNumber = userMuscleFatEntity.getUserMuscleFatNumber();
        this.userId = userMuscleFatEntity.getUserId(); 
        this.weight = userMuscleFatEntity.getWeight();
        this.skeletalMuscleMass = userMuscleFatEntity.getSkeletalMuscleMass();
        this.bodyFatMass = userMuscleFatEntity.getBodyFatMass();
        this.userMuscleFatDate = userMuscleFatEntity.getUserMuscleFatDate();
    }

    public static ResponseEntity<GetUserMuscleFatResponseDto> success(UserMuscleFatEntity userMuscleFatEntity) {
        GetUserMuscleFatResponseDto responseBody = new GetUserMuscleFatResponseDto(userMuscleFatEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}