package com.example.healthcare_back.service;

import java.util.List;

import com.example.healthcare_back.dto.request.schedule.PatchHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PatchMealScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostMealScheduleRequestDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetMealScheduleResponseDto;

public interface ScheduleService {
    
    // HealthSchedule
    List<GetHealthScheduleResponseDto> getAllHealthSchedules(String userId);
    GetHealthScheduleResponseDto getHealthScheduleById(Integer healthScheduleNumber);
    PostHealthScheduleRequestDto postHealthSchedule(PostHealthScheduleRequestDto dto);
    PatchHealthScheduleRequestDto patchHealthSchedule(Integer healthScheduleNumber, PatchHealthScheduleRequestDto dto);
    void deleteHealthSchedule(Integer healthScheduleNumber);

    // MealSchedule
    List<GetMealScheduleResponseDto> getAllMealSchedules(String userId);
    GetMealScheduleResponseDto getMealScheduleById(Integer mealScheduleNumber);
    PostMealScheduleRequestDto postMealSchedule(PostMealScheduleRequestDto dto);
    PatchMealScheduleRequestDto patchMealSchedule(Integer mealScheduleNumber, PatchMealScheduleRequestDto dto);
    void deleteMealSchedule(Integer mealScheduleNumber);
}
    
