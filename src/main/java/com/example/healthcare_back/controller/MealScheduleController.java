package com.example.healthcare_back.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
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

import com.example.healthcare_back.common.util.CsvUtil;
import com.example.healthcare_back.dto.request.schedule.PatchMealScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostMealScheduleRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetMealScheduleListResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetMealScheduleResponseDto;
import com.example.healthcare_back.service.MealScheduleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/meal-schedule")
@RequiredArgsConstructor
public class MealScheduleController {
    
    private final MealScheduleService mealScheduleService;
    private final CsvUtil csvUtil;

    @PostMapping("/search")
    public ResponseEntity<List<Map<String, Object>>> searchFood(@RequestBody Map<String, String> requestBody) {
        String keyword = requestBody.get("keyword");
        if (keyword == null || keyword.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ArrayList<>()); // 400 Bad Request
        }
        List<Map<String, Object>> searchResults = csvUtil.searchFoodData(keyword);
        return ResponseEntity.ok(searchResults);
    }
    
    @GetMapping("/food-data")
    public ResponseEntity<List<Map<String, Object>>> getFoodData() {
        try {
            List<Map<String, Object>> foodData = csvUtil.importFoodDataAsMap();
            return ResponseEntity.ok(foodData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 식단 일정 상세 조회
    @GetMapping("/{mealScheduleNumber}")
    public ResponseEntity<? super GetMealScheduleResponseDto> getMealSchedule(
            @PathVariable("mealScheduleNumber") Integer mealScheduleNumber,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetMealScheduleResponseDto> response = mealScheduleService.getMealSchedule(mealScheduleNumber, userId);
        return response;
    }

    // 식단 일정 리스트 조회
    @GetMapping(value = { "", "/" })
    public ResponseEntity<? super GetMealScheduleListResponseDto> getMealScheduleList(
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetMealScheduleListResponseDto> response = mealScheduleService.getMealScheduleList(userId);
        return response;
    }

    // 식단 일정 생성
    @PostMapping(value = { "", "/" })
    public ResponseEntity<ResponseDto> postMealSchedule(
            @RequestBody @Valid PostMealScheduleRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = mealScheduleService.postMealSchedule(requestBody, userId);
        return response;
    }

    // 식단 일정 수정
    @PatchMapping("/{mealScheduleNumber}")
    public ResponseEntity<ResponseDto> patchMealSchedule(
            @RequestBody @Valid PatchMealScheduleRequestDto requestBody,
            @PathVariable("mealScheduleNumber") Integer mealScheduleNumber,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = mealScheduleService.patchMealSchedule(requestBody, mealScheduleNumber, userId);
        return response;
    }

    // 식단 일정 삭제
    @DeleteMapping("/{mealScheduleNumber}")
    public ResponseEntity<ResponseDto> deleteMealSchedule(
            @PathVariable("mealScheduleNumber") Integer mealScheduleNumber,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = mealScheduleService.deleteMealSchedule(mealScheduleNumber, userId);
        return response;
    }

    // 식단 상세 일정 삭제
    @DeleteMapping("//meal-schedule-detail/{mealScheduleDetailNumber}")
    public ResponseEntity<ResponseDto> deleteMealScheduleDetail(
            @PathVariable("mealScheduleDetailNumber") Integer mealScheduleDetailNumber,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = mealScheduleService.deleteMealScheduleDetail(mealScheduleDetailNumber, userId);
        return response;
    }

}


