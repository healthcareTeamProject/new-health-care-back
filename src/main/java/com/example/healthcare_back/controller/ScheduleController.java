package com.example.healthcare_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.healthcare_back.dto.request.schedule.PatchHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostHealthScheduleRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleListResponseDto;
import com.example.healthcare_back.service.ScheduleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    
    private final ScheduleService scheduleService;

    @GetMapping("/{userId}/health-schedule-list")
    public ResponseEntity<? super GetHealthScheduleListResponseDto> getHealthScheduleList(
            @PathVariable String userId
    ) {
        ResponseEntity<? super GetHealthScheduleListResponseDto> response = scheduleService.getHealthScheduleList(userId);
        return response;
    }

    // 운동 일정 생성
    @PostMapping("/health-schedule/{userId}")
    public ResponseEntity<ResponseDto> postHealthSchedule(
            @RequestBody @Valid PostHealthScheduleRequestDto requestBody,
            @PathVariable String userId
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
   
}


