package com.example.healthcare_back.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.example.healthcare_back.entity.schedule.MealScheduleDetailEntity;

@Component
public class CsvUtil {
    private final CsvFileProperties csvFileProperties; // CSV 파일 경로 및 설정을 관리하는 프로퍼티 클래스
    private final ResourceLoader resourceLoader; // 리소스 파일 로딩을 위한 Spring 제공 도구

    public CsvUtil(CsvFileProperties csvFileProperties, ResourceLoader resourceLoader) {
        this.csvFileProperties = csvFileProperties; // CSV 파일 설정 주입
        this.resourceLoader = resourceLoader; // 리소스 로더 주입
    }

    /**
     * CSV 파일로부터 식품 데이터를 읽어와 Map 형식의 목록으로 반환하는 메서드
     * (클라이언트에서 사용할 간단한 데이터 구조 반환)
     */
    public List<Map<String, Object>> importFoodDataAsMap() {
        List<Map<String, Object>> foodDataList = new ArrayList<>();
        // 설정 파일에서 지정된 모든 CSV 파일 경로를 순회
        for (String filePath : csvFileProperties.getMealDataCsvPaths()) {
            System.out.println("Attempting to read file: " + filePath);
            try {
                // ResourceLoader를 사용해 파일 로드
                Resource resource = resourceLoader.getResource(filePath);
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
                     Stream<String> lines = br.lines()) { // 스트림 방식으로 파일 읽기
                    lines.skip(1) // 첫 번째 행(헤더) 건너뛰기
                         .forEach(line -> {
                             try {
                                 System.out.println("Reading line: " + line);
                                 String[] data = line.split(","); // CSV 데이터를 콤마로 분리
                                 if (data.length < 2) { // 데이터가 부족할 경우 처리 중단
                                     System.out.println("Invalid line: " + line);
                                     return;
                                 }
                                 Map<String, Object> foodData = new HashMap<>();
                                 foodData.put("mealName", data[0].trim()); // 첫 번째 값: 식사 이름
                                 foodData.put("mealKcal", new BigDecimal(data[1].trim())); // 두 번째 값: 칼로리
                                 foodDataList.add(foodData); // 결과 리스트에 추가
                             } catch (Exception e) { // 라인 처리 중 오류 발생 시 로깅
                                 System.out.println("Error processing line: " + line);
                                 e.printStackTrace();
                             }
                         });
                }
            } catch (Exception exception) { // 파일 읽기 실패 시 로깅
                System.out.println("Error processing file: " + exception.getMessage());
                exception.printStackTrace();
            }
        }
        System.out.println("Parsed Food Data: " + foodDataList);
        return foodDataList; // 읽어온 데이터 반환
    }

    /**
     * CSV 파일로부터 식사 데이터를 읽어와 MealScheduleDetailEntity 객체 목록으로 반환하는 메서드
     * (백엔드에서 저장하거나 가공할 데이터 구조 반환)
     */
    public List<MealScheduleDetailEntity> importMealDataFromCsv() {
        List<MealScheduleDetailEntity> allMealDetails = new ArrayList<>();
        // 설정 파일에서 지정된 모든 CSV 파일 경로를 순회
        for (String filePath : csvFileProperties.getMealDataCsvPaths()) {
            System.out.println("Attempting to read file: " + filePath);
            try {
                // ResourceLoader를 사용해 파일 로드
                Resource resource = resourceLoader.getResource(filePath);
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
                     Stream<String> lines = br.lines()) { // 스트림 방식으로 파일 읽기
                    lines.skip(1) // 첫 번째 행(헤더) 건너뛰기
                         .forEach(line -> {
                             try {
                                 System.out.println("Reading line: " + line);
                                 String[] data = line.split(","); // CSV 데이터를 콤마로 분리
                                 if (data.length < 2) { // 데이터가 부족할 경우 처리 중단
                                     System.out.println("Invalid line: " + line);
                                     return;
                                 }
                                 String mealName = data[0].trim(); // 식사 이름
                                 BigDecimal mealKcal = new BigDecimal(data[1].trim()); // 칼로리

                                 MealScheduleDetailEntity detail = new MealScheduleDetailEntity(
                                         mealName, mealKcal, 1, null // 엔티티 생성
                                 );
                                 allMealDetails.add(detail); // 결과 리스트에 추가
                             } catch (Exception e) { // 라인 처리 중 오류 발생 시 로깅
                                 System.out.println("Error processing line: " + line);
                                 e.printStackTrace();
                             }
                         });
                }
            } catch (Exception exception) { // 파일 읽기 실패 시 로깅
                System.out.println("Error processing file: " + exception.getMessage());
                exception.printStackTrace();
            }
        }
        System.out.println("Parsed Meal Details: " + allMealDetails);
        return allMealDetails; // 읽어온 데이터 반환
    }

    /**
     * 검색어를 기반으로 CSV 파일에서 필터링된 식품 데이터를 반환하는 메서드
     * @param keyword 검색어
     * @return 검색어가 포함된 식품 데이터 목록
     */
    public List<Map<String, Object>> searchFoodData(String keyword) {
        List<Map<String, Object>> searchResults = new ArrayList<>();
        // 설정 파일에서 지정된 모든 CSV 파일 경로를 순회
        for (String filePath : csvFileProperties.getMealDataCsvPaths()) {
            System.out.println("Attempting to read file for search: " + filePath);
            try {
                // ResourceLoader를 사용해 파일 로드
                Resource resource = resourceLoader.getResource(filePath);
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
                     Stream<String> lines = br.lines()) { // 스트림 방식으로 파일 읽기
                    lines.skip(1) // 첫 번째 행(헤더) 건너뛰기
                         .forEach(line -> {
                             try {
                                 String[] data = line.split(","); // CSV 데이터를 콤마로 분리
                                 if (data.length < 2) { // 데이터가 부족할 경우 처리 중단
                                     System.out.println("Invalid line during search: " + line);
                                     return;
                                 }
                                 String mealName = data[0].trim(); // 식사 이름
                                 if (mealName.contains(keyword)) { // 검색어 포함 여부 확인
                                     Map<String, Object> foodData = new HashMap<>();
                                     foodData.put("mealName", mealName); // 식사 이름
                                     foodData.put("mealKcal", new BigDecimal(data[1].trim())); // 칼로리
                                     searchResults.add(foodData); // 결과 리스트에 추가
                                 }
                             } catch (Exception e) { // 라인 처리 중 오류 발생 시 로깅
                                 System.out.println("Error processing line during search: " + line);
                                 e.printStackTrace();
                             }
                         });
                }
            } catch (Exception exception) { // 파일 읽기 실패 시 로깅
                System.out.println("Error processing file for search: " + exception.getMessage());
                exception.printStackTrace();
            }
        }
        System.out.println("Search Results: " + searchResults);
        return searchResults; // 검색 결과 반환
    }
}
