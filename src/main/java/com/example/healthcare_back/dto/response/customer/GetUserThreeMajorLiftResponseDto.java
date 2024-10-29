package com.example.healthcare_back.dto.response.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.UserThreeMajorLiftEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;

// 회원 3대측정 정보 조회 Response DTO

@Getter
public class GetUserThreeMajorLiftResponseDto extends ResponseDto {
    private final Integer userThreeMajorLiftNumber;
    private final String userId;
    private final BigDecimal deadlift;
    private final BigDecimal benchPress;
    private final BigDecimal squat;
    private final LocalDateTime userThreeMajorLiftDate;
    
    public GetUserThreeMajorLiftResponseDto(UserThreeMajorLiftEntity userThreeMajorLiftEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.userThreeMajorLiftNumber = userThreeMajorLiftEntity.getUserThreeMajorLiftNumber();
        this.userId = userThreeMajorLiftEntity.getUserId(); 
        this.deadlift = userThreeMajorLiftEntity.getDeadlift();
        this.benchPress = userThreeMajorLiftEntity.getBenchPress();
        this.squat = userThreeMajorLiftEntity.getSquat();
        this.userThreeMajorLiftDate = userThreeMajorLiftEntity.getUserThreeMajorLiftDate();
    }

    public static ResponseEntity<GetUserThreeMajorLiftResponseDto> success(UserThreeMajorLiftEntity userThreeMajorLiftEntity) {
        GetUserThreeMajorLiftResponseDto responseBody = new GetUserThreeMajorLiftResponseDto(userThreeMajorLiftEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}