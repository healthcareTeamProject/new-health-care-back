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
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetMealScheduleResponseDto;
import com.example.healthcare_back.entity.schedule.HealthScheduleEntity;

public interface ScheduleService {
    
    // HealthSchedule
    // ResponseEntity<List<? super GetHealthScheduleResponseDto>> getAllSchedules(String userId);
    // ResponseEntity<? super GetHealthScheduleResponseDto> getHealthScheduleById(Integer healthScheduleNumber);
    
    // 특정 날짜 범위 내 일정 조회
    List<HealthScheduleEntity> findByHealthScheduleStartBetween(LocalDateTime start, LocalDateTime end);
    // 특정 유저의 일정 조회
    List<HealthScheduleEntity> findByUserId(String userId);
    ResponseEntity<ResponseDto> postHealthSchedule(PostHealthScheduleRequestDto dto);
    ResponseEntity<ResponseDto> patchHealthSchedule(Integer healthScheduleNumber, PatchHealthScheduleRequestDto dto);
    ResponseEntity<ResponseDto> deleteHealthSchedule(Integer healthScheduleNumber);

    // MealSchedule
    // ResponseEntity<List<? super GetMealScheduleResponseDto>> getAllMealSchedules(String userId);
    // ResponseEntity<? super GetMealScheduleResponseDto> getMealScheduleById(String userId);
    ResponseEntity<ResponseDto> postMealSchedule(PostMealScheduleRequestDto dto);
    ResponseEntity<ResponseDto> patchMealSchedule(Integer mealScheduleNumber, PatchMealScheduleRequestDto dto);
    ResponseEntity<ResponseDto> deleteMealSchedule(Integer mealScheduleNumber);
}
    
