package com.example.healthcare_back.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

// import java.util.List;

import com.example.healthcare_back.dto.request.schedule.PatchHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PatchMealScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostMealScheduleRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleListResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetMealScheduleListResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetMealScheduleResponseDto;

public interface ScheduleService {
    
    // HealthSchedule
    ResponseEntity<? super GetHealthScheduleResponseDto> getHealthSchedule(Integer healthScheduleNumber, String userId);
    ResponseEntity<? super GetHealthScheduleListResponseDto> getHealthScheduleList(String userId);
    ResponseEntity<ResponseDto> postHealthSchedule(PostHealthScheduleRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchHealthSchedule(PatchHealthScheduleRequestDto dto, Integer healthScheduleNumber, String userId);
    ResponseEntity<ResponseDto> deleteHealthSchedule(Integer healthScheduleNumber, String userId);

    // MealSchedule
    ResponseEntity<? super GetMealScheduleResponseDto> getMealSchedule(Integer mealScheduleNumber, String userId);
    ResponseEntity<? super GetMealScheduleListResponseDto> getMealScheduleList(String userId);
    ResponseEntity<ResponseDto> postMealSchedule(PostMealScheduleRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchMealSchedule(PatchMealScheduleRequestDto dto, Integer mealScheduleNumber, String userId);
    ResponseEntity<ResponseDto> deleteMealSchedule(Integer mealScheduleNumber, String userId);
    ResponseEntity<ResponseDto> deleteMealScheduleDetail(Integer mealScheduleDetailNumber, String userId);

    ResponseEntity<List<Map<String, Object>>> getAllSchedulesForCalendar(String userId);
    
}
    
