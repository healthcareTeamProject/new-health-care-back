// package com.example.healthcare_back.service.implement;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// import org.springframework.stereotype.Service;
// import org.springframework.web.reactive.function.client.WebClient;
// import org.springframework.http.ResponseEntity;

// import com.example.healthcare_back.config.MealApiConfig;
// import com.example.healthcare_back.dto.response.schedule.MealDetailFoodApiResponseDto;
// import com.example.healthcare_back.service.MealDetailService;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class MealDetailServiceImplement implements MealDetailService {

//     private final WebClient webClient; 
//     private final MealApiConfig mealApiConfig;

//     @Override
//     public List<MealDetailFoodApiResponseDto> getAllMealDetails() {
//         List<MealDetailFoodApiResponseDto> mealDetails = new ArrayList<>();

//         List<String> apiUrls = Arrays.asList(
//             mealApiConfig.getProcessUrl() + mealApiConfig.getKey(),
//             mealApiConfig.getFoodUrl() + mealApiConfig.getKey(),
//             mealApiConfig.getMaterialUrl() + mealApiConfig.getKey(),
//             mealApiConfig.getNutriUrl() + mealApiConfig.getKey()
//         );

//         for (String apiUrl : apiUrls) {
//             try {
//                 ResponseEntity<MealDetailFoodApiResponseDto[]> response = webClient.get()
//                         .uri(apiUrl)
//                         .retrieve()
//                         .toEntity(MealDetailFoodApiResponseDto[].class)
//                         .block();  // block()을 사용하여 동기적으로 처리

//                 if (response != null && response.getBody() != null) {
//                     mealDetails.addAll(Arrays.asList(response.getBody()));
//                 }
//             } catch (Exception e) {
//                 System.out.println("API 호출 실패: " + e.getMessage());
//             }
//         }
//         return mealDetails;
//     }

//     @Override
//     public List<MealDetailFoodApiResponseDto> searchMealDetailsByName(String mealName) {
//         List<MealDetailFoodApiResponseDto> mealDetails = new ArrayList<>();

//         List<String> apiUrls = Arrays.asList(
//             mealApiConfig.getProcessUrl() + mealApiConfig.getKey() + "&query=" + mealName,
//             mealApiConfig.getFoodUrl() + mealApiConfig.getKey() + "&query=" + mealName,
//             mealApiConfig.getMaterialUrl() + mealApiConfig.getKey() + "&query=" + mealName,
//             mealApiConfig.getNutriUrl() + mealApiConfig.getKey() + "&query=" + mealName
//         );

//         for (String apiUrl : apiUrls) {
//             try {
//                 ResponseEntity<MealDetailFoodApiResponseDto[]> response = webClient.get()
//                         .uri(apiUrl)
//                         .retrieve()
//                         .toEntity(MealDetailFoodApiResponseDto[].class)
//                         .block();

//                 if (response != null && response.getBody() != null) {
//                     mealDetails.addAll(Arrays.asList(response.getBody()));
//                 }
//             } catch (Exception e) {
//                 System.out.println("API 호출 실패: " + e.getMessage());
//             }
//         }
//         return mealDetails;
//     }
// }
