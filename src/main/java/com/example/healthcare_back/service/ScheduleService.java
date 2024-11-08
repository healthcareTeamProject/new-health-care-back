package com.example.healthcare_back.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;

// import java.util.List;

import com.example.healthcare_back.dto.request.schedule.PatchHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PatchMealScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostMealScheduleRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleListResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleResponseDto;

public interface ScheduleService {
    
    // HealthSchedule
    ResponseEntity<? super GetHealthScheduleListResponseDto> getHealthScheduleList(String userId);
    ResponseEntity<ResponseDto> postHealthSchedule(PostHealthScheduleRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchHealthSchedule(PatchHealthScheduleRequestDto dto, Integer healthScheduleNumber);
    ResponseEntity<ResponseDto> deleteHealthSchedule(Integer healthScheduleNumber);

    // MealSchedule
    // List<GetHealthScheduleResponseDto> getMealSchedules(String userId); // 운동 일정 조회
    // ResponseEntity<ResponseDto> postMealSchedule(PostMealScheduleRequestDto dto, String userId);
    // ResponseEntity<ResponseDto> patchMealSchedule(PatchMealScheduleRequestDto dto, Integer mealthScheduleNumber);
    // ResponseEntity<ResponseDto> deleteMealSchedule(Integer mealthScheduleNumber);
}
    
