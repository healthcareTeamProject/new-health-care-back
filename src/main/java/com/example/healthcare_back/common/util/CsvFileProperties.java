package com.example.healthcare_back.common.util;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
public class CsvFileProperties {

    private List<String> mealDataCsvPaths;

    public List<String> getMealDataCsvPaths() {
        return mealDataCsvPaths;
    }

    public void setMealDataCsvPaths(List<String> mealDataCsvPaths) {
        this.mealDataCsvPaths = mealDataCsvPaths;
    }
}
