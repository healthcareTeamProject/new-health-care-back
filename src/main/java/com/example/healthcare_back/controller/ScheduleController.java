package com.example.healthcare_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.healthcare_back.dto.request.schedule.PatchHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PatchMealScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostMealScheduleRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleListResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetMealScheduleListResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetMealScheduleResponseDto;
import com.example.healthcare_back.service.ScheduleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    
    private final ScheduleService scheduleService;

    // 운동 일정 상세 조회
    @GetMapping("/health-schedule/{healthScheduleNumber}")
    public ResponseEntity<? super GetHealthScheduleResponseDto> getHealthSchedule(
            @PathVariable Integer healthScheduleNumber, 
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetHealthScheduleResponseDto> response = scheduleService.getHealthSchedule(healthScheduleNumber, userId);
        return response;
    }

    // 운동 일정 리스트 조회
    @GetMapping("/health-schedule-list")
    public ResponseEntity<? super GetHealthScheduleListResponseDto> getHealthScheduleList(
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetHealthScheduleListResponseDto> response = scheduleService.getHealthScheduleList(userId);
        return response;
    }

    // 운동 일정 생성
    @PostMapping("/health-schedule")
    public ResponseEntity<ResponseDto> postHealthSchedule(
            @RequestBody @Valid PostHealthScheduleRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = scheduleService.postHealthSchedule(requestBody, userId);
        return response;
    }

    // 운동 일정 수정
    @PatchMapping("/health-schedule/{healthScheduleNumber}")
    public ResponseEntity<ResponseDto> patchHealthSchedule(
            @RequestBody @Valid PatchHealthScheduleRequestDto requestBody,
            @PathVariable Integer healthScheduleNumber
    ) {
        ResponseEntity<ResponseDto> response = scheduleService.patchHealthSchedule(requestBody, healthScheduleNumber);
        return response;
    }

    // 운동 일정 삭제
    @DeleteMapping("/health-schedule/{healthScheduleNumber}")
    public ResponseEntity<ResponseDto> deleteHealthSchedule(
            @PathVariable Integer healthScheduleNumber
    ) {
        ResponseEntity<ResponseDto> response = scheduleService.deleteHealthSchedule(healthScheduleNumber);
        return response;
    }

    // ===================================================================

    // 식단 일정 상세 조회
    @GetMapping("/meal-schedule/{mealScheduleNumber}")
    public ResponseEntity<? super GetMealScheduleResponseDto> getMealSchedule(
            @PathVariable Integer mealScheduleNumber,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetMealScheduleResponseDto> response = scheduleService.getMealSchedule(mealScheduleNumber, userId);
        return response;
    }

    // 식단 일정 리스트 조회
    @GetMapping("/meal-schedule-list")
    public ResponseEntity<? super GetMealScheduleListResponseDto> getMealScheduleList(
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetMealScheduleListResponseDto> response = scheduleService.getMealScheduleList(userId);
        return response;
    }

    // 식단 일정 생성
    @PostMapping("/meal-schedule")
    public ResponseEntity<ResponseDto> postMealSchedule(
            @RequestBody @Valid PostMealScheduleRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = scheduleService.postMealSchedule(requestBody, userId);
        return response;
    }

    // 식단 일정 수정
    @PatchMapping("/meal-schedule/{mealScheduleNumber}")
    public ResponseEntity<ResponseDto> patchMealSchedule(
            @RequestBody @Valid PatchMealScheduleRequestDto requestBody,
            @PathVariable Integer mealScheduleNumber
    ) {
        ResponseEntity<ResponseDto> response = scheduleService.patchMealSchedule(requestBody, mealScheduleNumber);
        return response;
    }

    // 식단 일정 삭제
    @DeleteMapping("/meal-schedule/{mealScheduleNumber}")
    public ResponseEntity<ResponseDto> deleteMealSchedule(
            @PathVariable Integer mealScheduleNumber
    ) {
        ResponseEntity<ResponseDto> response = scheduleService.deleteMealSchedule(mealScheduleNumber);
        return response;
    }

    // 식단 상세 일정 삭제
    @DeleteMapping("/meal-schedule-detail/{mealScheduleDetailNumber}")
    public ResponseEntity<ResponseDto> deleteMealScheduleDetail(
            @PathVariable Integer mealScheduleDetailNumber
    ) {
        ResponseEntity<ResponseDto> response = scheduleService.deleteMealScheduleDetail(mealScheduleDetailNumber);
        return response;
    }
   
}


