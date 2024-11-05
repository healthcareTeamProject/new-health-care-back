package com.example.healthcare_back.service;

import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.request.schedule.DeleteHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.DeleteMealScheduleRequestDto;

// import java.util.List;

import com.example.healthcare_back.dto.request.schedule.PatchHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PatchMealScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostMealScheduleRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetMealScheduleResponseDto;

public interface ScheduleService {
    
    // HealthSchedule
    // List<GetHealthScheduleResponseDto> getAllHealthSchedules(String userId);
    ResponseEntity<GetHealthScheduleResponseDto> getHealthScheduleById(String userId);
    ResponseEntity<ResponseDto> postHealthSchedule(String userId, PostHealthScheduleRequestDto dto);
    ResponseEntity<ResponseDto> patchHealthSchedule(String userId, PatchHealthScheduleRequestDto dto);
    ResponseEntity<ResponseDto> deleteHealthSchedule(String userId, DeleteHealthScheduleRequestDto dto);

    // MealSchedule
    // List<GetMealScheduleResponseDto> getAllMealSchedules(String userId);
    ResponseEntity<GetMealScheduleResponseDto> getMealScheduleById(String userId);
    ResponseEntity<ResponseDto> postMealSchedule(String userId, PostMealScheduleRequestDto dto);
    ResponseEntity<ResponseDto> patchMealSchedule(String userId, PatchMealScheduleRequestDto dto);
    ResponseEntity<ResponseDto> deleteMealSchedule(String userId, DeleteMealScheduleRequestDto dto);
}
    
