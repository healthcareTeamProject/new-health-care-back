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
    private final CsvFileProperties csvFileProperties;
    private final ResourceLoader resourceLoader;

    public CsvUtil(CsvFileProperties csvFileProperties, ResourceLoader resourceLoader) {
        this.csvFileProperties = csvFileProperties;
        this.resourceLoader = resourceLoader;
    }

    /**
     * CSV 파일로부터 식품 데이터를 읽어와 Map 형식의 목록으로 반환하는 메서드
     * (클라이언트에서 사용할 간단한 데이터 구조 반환)
     */
    public List<Map<String, Object>> importFoodDataAsMap() {
        List<Map<String, Object>> foodDataList = new ArrayList<>();
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
                                 String[] data = line.split(",");
                                 if (data.length < 2) {
                                     System.out.println("Invalid line: " + line);
                                     return;
                                 }
                                 Map<String, Object> foodData = new HashMap<>();
                                 foodData.put("mealName", data[0].trim());
                                 foodData.put("mealKcal", new BigDecimal(data[1].trim()));
                                 foodDataList.add(foodData);
                             } catch (Exception e) {
                                 System.out.println("Error processing line: " + line);
                                 e.printStackTrace();
                             }
                         });
                }
            } catch (Exception exception) {
                System.out.println("Error processing file: " + exception.getMessage());
                exception.printStackTrace();
            }
        }
        System.out.println("Parsed Food Data: " + foodDataList);
        return foodDataList;
    }

    /**
     * CSV 파일로부터 식사 데이터를 읽어와 MealScheduleDetailEntity 객체 목록으로 반환하는 메서드
     * (백엔드에서 저장하거나 가공할 데이터 구조 반환)
     */
    public List<MealScheduleDetailEntity> importMealDataFromCsv() {
        List<MealScheduleDetailEntity> allMealDetails = new ArrayList<>();
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
                                 String[] data = line.split(",");
                                 if (data.length < 2) {
                                     System.out.println("Invalid line: " + line);
                                     return;
                                 }
                                 String mealName = data[0].trim();
                                 BigDecimal mealKcal = new BigDecimal(data[1].trim());

                                 MealScheduleDetailEntity detail = new MealScheduleDetailEntity(
                                         mealName, mealKcal, 1, null
                                 );
                                 allMealDetails.add(detail);
                             } catch (Exception e) {
                                 System.out.println("Error processing line: " + line);
                                 e.printStackTrace();
                             }
                         });
                }
            } catch (Exception exception) {
                System.out.println("Error processing file: " + exception.getMessage());
                exception.printStackTrace();
            }
        }
        System.out.println("Parsed Meal Details: " + allMealDetails);
        return allMealDetails;
    }

    /**
     * 검색어를 기반으로 CSV 파일에서 필터링된 식품 데이터를 반환하는 메서드
     * @param keyword 검색어
     * @return 검색어가 포함된 식품 데이터 목록
     */
    public List<Map<String, Object>> searchFoodData(String keyword) {
        List<Map<String, Object>> searchResults = new ArrayList<>();
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
                                 String[] data = line.split(",");
                                 if (data.length < 2) {
                                     System.out.println("Invalid line during search: " + line);
                                     return;
                                 }
                                 String mealName = data[0].trim();
                                 if (mealName.contains(keyword)) { // 검색어 포함 여부 확인
                                     Map<String, Object> foodData = new HashMap<>();
                                     foodData.put("mealName", mealName);
                                     foodData.put("mealKcal", new BigDecimal(data[1].trim()));
                                     searchResults.add(foodData);
                                 }
                             } catch (Exception e) {
                                 System.out.println("Error processing line during search: " + line);
                                 e.printStackTrace();
                             }
                         });
                }
            } catch (Exception exception) {
                System.out.println("Error processing file for search: " + exception.getMessage());
                exception.printStackTrace();
            }
        }
        System.out.println("Search Results: " + searchResults);
        return searchResults;
    }
}
