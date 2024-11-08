package com.example.healthcare_back.dto.response.schedule;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.schedule.HealthScheduleEntity;

import lombok.Getter;

@Getter
public class GetHealthScheduleResponseDto extends ResponseDto{
    
    private Integer healthScheduleNumber;
    private String userId;
    private String healthTitle;
    private LocalDateTime healthScheduleStart;
    private LocalDateTime healthScheduleEnd;
    
    public GetHealthScheduleResponseDto(HealthScheduleEntity healthScheduleEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.healthScheduleNumber = healthScheduleEntity.getHealthScheduleNumber();
        this.userId = healthScheduleEntity.getUserId();
        this.healthTitle = healthScheduleEntity.getHealthTitle();
        this.healthScheduleStart = healthScheduleEntity.getHealthScheduleStart();
        this.healthScheduleEnd = healthScheduleEntity.getHealthScheduleEnd();
    }

    public static ResponseEntity<GetHealthScheduleResponseDto> success(HealthScheduleEntity healthScheduleEntity) {
        GetHealthScheduleResponseDto responseBody = new GetHealthScheduleResponseDto(healthScheduleEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
