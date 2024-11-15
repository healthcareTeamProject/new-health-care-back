package com.example.healthcare_back.service;

import org.springframework.http.ResponseEntity;

// import java.util.List;

import com.example.healthcare_back.dto.request.schedule.PatchHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostHealthScheduleRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleListResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleResponseDto;

public interface HealthScheduleService {
    
    // HealthSchedule
    ResponseEntity<? super GetHealthScheduleResponseDto> getHealthSchedule(Integer healthScheduleNumber, String userId);
    ResponseEntity<? super GetHealthScheduleListResponseDto> getHealthScheduleList(String userId);
    ResponseEntity<ResponseDto> postHealthSchedule(PostHealthScheduleRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchHealthSchedule(PatchHealthScheduleRequestDto dto, Integer healthScheduleNumber, String userId);
    ResponseEntity<ResponseDto> deleteHealthSchedule(Integer healthScheduleNumber, String userId);

}
    
