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

    // 생성자: HealthScheduleEntity 리스트를 받아 성공 코드와 메시지를 설정하고,
    // HealthScheduleList로 변환하여 healthScheduleLists 필드에 할당
    public GetHealthScheduleListResponseDto(List<HealthScheduleEntity> healthScheduleEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.healthScheduleLists = HealthScheduleList.getList(healthScheduleEntities);
    }
    
    // 정적 메서드: 성공적인 요청에 대한 ResponseEntity 객체를 생성하여 반환
    public static ResponseEntity<GetHealthScheduleListResponseDto> success(List<HealthScheduleEntity> healthScheduleEntities) {
        // 응답 본문으로 사용할 GetHealthScheduleListResponseDto 객체 생성
        GetHealthScheduleListResponseDto responseBody = new GetHealthScheduleListResponseDto(healthScheduleEntities);
        // HTTP 상태 코드를 OK(200)로 설정하여 응답 반환
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
    
