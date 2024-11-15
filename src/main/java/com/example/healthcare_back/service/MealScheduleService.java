package com.example.healthcare_back.service;

import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.request.schedule.PatchMealScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostMealScheduleRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetMealScheduleListResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetMealScheduleResponseDto;

public interface MealScheduleService {

    ResponseEntity<? super GetMealScheduleResponseDto> getMealSchedule(Integer mealScheduleNumber, String userId);
    ResponseEntity<? super GetMealScheduleListResponseDto> getMealScheduleList(String userId);
    ResponseEntity<ResponseDto> postMealSchedule(PostMealScheduleRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchMealSchedule(PatchMealScheduleRequestDto dto, Integer mealScheduleNumber, String userId);
    ResponseEntity<ResponseDto> deleteMealSchedule(Integer mealScheduleNumber, String userId);
    ResponseEntity<ResponseDto> deleteMealScheduleDetail(Integer mealScheduleDetailNumber, String userId);
    
}
