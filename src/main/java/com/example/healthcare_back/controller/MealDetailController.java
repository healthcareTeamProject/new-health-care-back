// package com.example.healthcare_back.controller;

// import java.util.List;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.healthcare_back.dto.response.schedule.MealDetailFoodApiResponseDto;
// import com.example.healthcare_back.service.MealDetailService;

// import lombok.RequiredArgsConstructor;

// @RestController
// @RequestMapping("/api/v1/mealDetails")
// @RequiredArgsConstructor
// public class MealDetailController {
    
//     private final MealDetailService mealDetailService;

//     // 전체 식품 정보 가져오기
//     @GetMapping("/all")
//     public ResponseEntity<List<MealDetailFoodApiResponseDto>> getAllMealDetails() {
//         List<MealDetailFoodApiResponseDto> mealDetails = mealDetailService.getAllMealDetails();
//         if (mealDetails.isEmpty()) {
//             return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 데이터가 없으면 204 반환
//         }
//         return ResponseEntity.ok(mealDetails);
//     }

//     // 특정 이름으로 식품 정보 검색
//     @GetMapping("/search")
//     public ResponseEntity<List<MealDetailFoodApiResponseDto>> searchMealDetailsByName(@RequestParam String mealName) {
//         List<MealDetailFoodApiResponseDto> mealDetails = mealDetailService.searchMealDetailsByName(mealName);
//         if (mealDetails.isEmpty()) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mealDetails); // 데이터가 없으면 404 반환
//         }
//         return ResponseEntity.ok(mealDetails);
//     }
// }
