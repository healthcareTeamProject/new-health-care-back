package com.example.healthcare_back.common.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.healthcare_back.entity.schedule.MealScheduleDetailEntity;

@Component
public class CsvUtil {

    private final CsvFileProperties csvFileProperties;

    public CsvUtil(CsvFileProperties csvFileProperties) {
        this.csvFileProperties = csvFileProperties;
    }

    public List<MealScheduleDetailEntity> importMealDataFromCsv() {
        List<MealScheduleDetailEntity> allMealDetails = new ArrayList<>();

        for (String filePath : csvFileProperties.getMealDataCsvPaths()) {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                br.readLine(); // 헤더 행 건너뜀
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    String mealName = data[0].trim();
                    BigDecimal mealKcal = new BigDecimal(data[1].trim());

                    MealScheduleDetailEntity detail = new MealScheduleDetailEntity(
                        mealName, mealKcal, 1, null
                    );
                    allMealDetails.add(detail);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return allMealDetails;
    }

}
