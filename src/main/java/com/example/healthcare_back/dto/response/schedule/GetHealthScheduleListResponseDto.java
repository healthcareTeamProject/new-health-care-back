package com.example.healthcare_back.dto.response.schedule;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.common.object.HealthScheduleList;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.schedule.HealthScheduleEntity;

import lombok.Getter;

@Getter
public class GetHealthScheduleListResponseDto extends ResponseDto {

    private final List<HealthScheduleList> healthScheduleLists;

    public GetHealthScheduleListResponseDto(List<HealthScheduleEntity> healthScheduleEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.healthScheduleLists = HealthScheduleList.getList(healthScheduleEntities);
    }

    public static ResponseEntity<GetHealthScheduleListResponseDto> success(List<HealthScheduleEntity> healthScheduleEntities) {
        GetHealthScheduleListResponseDto responseBody = new GetHealthScheduleListResponseDto(healthScheduleEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
    
