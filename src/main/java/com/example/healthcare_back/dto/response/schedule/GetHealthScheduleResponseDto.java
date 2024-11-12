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
public class GetHealthScheduleResponseDto extends ResponseDto {
    
    private Integer healthScheduleNumber;
    private String healthTitle;
    private LocalDateTime healthScheduleStart;
    private LocalDateTime healthScheduleEnd;

    // HealthScheduleEntity를 받아 필드 초기화하는 생성자
    public GetHealthScheduleResponseDto(HealthScheduleEntity healthScheduleEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.healthScheduleNumber = healthScheduleEntity.getHealthScheduleNumber();
        this.healthTitle = healthScheduleEntity.getHealthTitle();
        this.healthScheduleStart = healthScheduleEntity.getHealthScheduleStart();
        this.healthScheduleEnd = healthScheduleEntity.getHealthScheduleEnd();
    }

    // 성공 응답을 반환하는 메서드
    public static ResponseEntity<GetHealthScheduleResponseDto> success(HealthScheduleEntity healthScheduleEntity) {
        GetHealthScheduleResponseDto responseBody = new GetHealthScheduleResponseDto(healthScheduleEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}