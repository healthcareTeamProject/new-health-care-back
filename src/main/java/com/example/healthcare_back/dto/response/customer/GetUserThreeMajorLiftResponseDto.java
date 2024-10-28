package com.example.healthcare_back.dto.response.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.UserThreeMajorLiftEntity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;

@Getter
public class GetUserThreeMajorLiftResponseDto extends ResponseDto {

    private Integer threeMajorLiftNumber;
    private String userId;
    private Double deadlift;
    private Double benchPress;
    private Double squat;
    private LocalDateTime threeMajorLiftDate;
    
    public GetUserThreeMajorLiftResponseDto(UserThreeMajorLiftEntity userThreeMajorLiftEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.threeMajorLiftNumber = userThreeMajorLiftEntity.getThreeMajorLiftNumber();
        this.userId = userThreeMajorLiftEntity.getUserId();
        this.deadlift = userThreeMajorLiftEntity.getDeadlift();
        this.benchPress = userThreeMajorLiftEntity.getBenchPress();
        this.squat = userThreeMajorLiftEntity.getSquat();
        this.threeMajorLiftDate = userThreeMajorLiftEntity.getThreeMajorLiftDate();
    }

    public static ResponseEntity<GetUserThreeMajorLiftResponseDto> success(List<UserThreeMajorLiftEntity> userThreeMajorLiftEntities) {
        GetUserThreeMajorLiftResponseDto responseBody = new GetUserThreeMajorLiftResponseDto((UserThreeMajorLiftEntity) userThreeMajorLiftEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
