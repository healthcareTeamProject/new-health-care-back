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
import com.example.healthcare_back.dto.request.schedule.PostHealthScheduleRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleListResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleResponseDto;
import com.example.healthcare_back.service.HealthScheduleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/health-schedule")
@RequiredArgsConstructor
public class HealthScheduleController {
    
    private final HealthScheduleService healthScheduleService;

    // 운동 일정 상세 조회
    @GetMapping("/{healthScheduleNumber}")
    public ResponseEntity<? super GetHealthScheduleResponseDto> getHealthSchedule(
            @PathVariable("healthScheduleNumber") Integer healthScheduleNumber, 
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetHealthScheduleResponseDto> response = healthScheduleService.getHealthSchedule(healthScheduleNumber, userId);
        return response;
    }

    // 운동 일정 리스트 조회
    @GetMapping(value = { "", "/" })
    public ResponseEntity<? super GetHealthScheduleListResponseDto> getHealthScheduleList(
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetHealthScheduleListResponseDto> response = healthScheduleService.getHealthScheduleList(userId);
        return response;
    }

    // 운동 일정 생성
    @PostMapping(value = { "", "/" })
    public ResponseEntity<ResponseDto> postHealthSchedule(
            @RequestBody @Valid PostHealthScheduleRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = healthScheduleService.postHealthSchedule(requestBody, userId);
        return response;
    }

    // 운동 일정 수정
    @PatchMapping("/{healthScheduleNumber}")
    public ResponseEntity<ResponseDto> patchHealthSchedule(
            @RequestBody @Valid PatchHealthScheduleRequestDto requestBody,
            @PathVariable("healthScheduleNumber") Integer healthScheduleNumber,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = healthScheduleService.patchHealthSchedule(requestBody, healthScheduleNumber, userId);
        return response;
    }

    // 운동 일정 삭제
    @DeleteMapping("/{healthScheduleNumber}")
    public ResponseEntity<ResponseDto> deleteHealthSchedule(
            @PathVariable("healthScheduleNumber") Integer healthScheduleNumber,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = healthScheduleService.deleteHealthSchedule(healthScheduleNumber, userId);
        return response;
    }
}


