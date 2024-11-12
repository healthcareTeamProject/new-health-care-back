package com.example.healthcare_back.common.util;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
public class CsvFileProperties {

    // 식사 데이터 CSV 파일 경로 목록
    private List<String> mealDataCsvPaths;

    // mealDataCsvPaths 값을 반환하는 getter 메서드
    public List<String> getMealDataCsvPaths() {
        return mealDataCsvPaths;
    }

    // mealDataCsvPaths 값을 설정하는 setter 메서드
    public void setMealDataCsvPaths(List<String> mealDataCsvPaths) {
        this.mealDataCsvPaths = mealDataCsvPaths;
    }
}
