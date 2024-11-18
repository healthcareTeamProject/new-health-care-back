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
        // 클라이언트가 POST 요청으로 검색어를 전달하는 메서드
        String keyword = requestBody.get("keyword"); // 요청 본문에서 'keyword' 값 추출
        if (keyword == null || keyword.trim().isEmpty()) {
            // 검색어가 없거나 빈 문자열일 경우 400 Bad Request 응답 반환
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
        // CSV 파일에서 검색어를 기반으로 데이터를 필터링
        List<Map<String, Object>> searchResults = csvUtil.searchFoodData(keyword);
        return ResponseEntity.ok(searchResults); // 200 OK와 함께 검색 결과 반환
    }
    
    @GetMapping("/food-data")
    public ResponseEntity<List<Map<String, Object>>> getFoodData() {
        try {
            // CSV 파일에서 모든 식품 데이터를 가져오는 메서드
            List<Map<String, Object>> foodData = csvUtil.importFoodDataAsMap();
            return ResponseEntity.ok(foodData); // 200 OK와 함께 데이터를 반환
        } catch (Exception e) {
            // 데이터 로드 중 예외가 발생할 경우 스택 트레이스 출력 및 500 Internal Server Error 반환
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


