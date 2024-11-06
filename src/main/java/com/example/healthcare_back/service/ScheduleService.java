package com.example.healthcare_back.service;

import java.util.List;

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
    // ResponseEntity<List<? super GetHealthScheduleResponseDto>> getAllSchedules(String userId);
    ResponseEntity<? super GetHealthScheduleResponseDto> getHealthScheduleById(Integer healthScheduleNumber);
    ResponseEntity<ResponseDto> postHealthSchedule(PostHealthScheduleRequestDto dto);
    ResponseEntity<ResponseDto> patchHealthSchedule(Integer healthScheduleNumber, PatchHealthScheduleRequestDto dto);
    ResponseEntity<ResponseDto> deleteHealthSchedule(Integer healthScheduleNumber);

    // MealSchedule
    // ResponseEntity<List<? super GetMealScheduleResponseDto>> getAllMealSchedules(String userId);
    ResponseEntity<? super GetMealScheduleResponseDto> getMealScheduleById(String userId);
    ResponseEntity<ResponseDto> postMealSchedule(PostMealScheduleRequestDto dto);
    ResponseEntity<ResponseDto> patchMealSchedule(Integer mealScheduleNumber, PatchMealScheduleRequestDto dto);
    ResponseEntity<ResponseDto> deleteMealSchedule(Integer mealScheduleNumber);
}
    
