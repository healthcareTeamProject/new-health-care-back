package com.example.healthcare_back.service;

import org.springframework.http.ResponseEntity;

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
    ResponseEntity<GetHealthScheduleResponseDto> getHealthScheduleById(Integer healthScheduleNumber);
    ResponseEntity<ResponseDto> postHealthSchedule(PostHealthScheduleRequestDto dto);
    ResponseEntity<ResponseDto> patchHealthSchedule(Integer healthScheduleNumber, PatchHealthScheduleRequestDto dto);
    ResponseEntity<ResponseDto> deleteHealthSchedule(Integer healthScheduleNumber);

    // MealSchedule
    // List<GetMealScheduleResponseDto> getAllMealSchedules(String userId);
    ResponseEntity<GetMealScheduleResponseDto> getMealScheduleById(Integer mealScheduleNumber);
    ResponseEntity<ResponseDto> postMealSchedule(PostMealScheduleRequestDto dto);
    ResponseEntity<ResponseDto> patchMealSchedule(Integer mealScheduleNumber, PatchMealScheduleRequestDto dto);
    ResponseEntity<ResponseDto> deleteMealSchedule(Integer mealScheduleNumber);
}
    
