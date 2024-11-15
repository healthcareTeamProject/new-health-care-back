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

    // CsvFileProperties 빈을 통해 CSV 파일 경로 설정을 주입받는 필드
    private final CsvFileProperties csvFileProperties;

    // CsvFileProperties를 주입받아 CsvUtil 객체를 생성하는 생성자
    public CsvUtil(CsvFileProperties csvFileProperties) {
        this.csvFileProperties = csvFileProperties;
    }

    /**
     * CSV 파일로부터 식사 데이터를 읽어와 MealScheduleDetailEntity 객체 목록으로 반환하는 메서드
     * @return 모든 CSV 파일에서 읽어온 MealScheduleDetailEntity 객체 목록
     */
    public List<MealScheduleDetailEntity> importMealDataFromCsv() {
        // 모든 식사 상세 데이터를 저장할 리스트
        List<MealScheduleDetailEntity> allMealDetails = new ArrayList<>();

        // 설정된 CSV 파일 경로들을 순회하면서 데이터를 읽음
        for (String filePath : csvFileProperties.getMealDataCsvPaths()) {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                // 첫 번째 행(헤더)을 건너뜀
                br.readLine();
                // CSV 파일의 각 행을 읽음
                while ((line = br.readLine()) != null) {
                    // 쉼표로 데이터를 분리
                    String[] data = line.split(",");
                    // 첫 번째 데이터는 식사 이름
                    String mealName = data[0].trim();
                    // 두 번째 데이터는 칼로리, BigDecimal로 변환
                    BigDecimal mealKcal = new BigDecimal(data[1].trim());

                    // MealScheduleDetailEntity 객체 생성
                    MealScheduleDetailEntity detail = new MealScheduleDetailEntity(
                        mealName, mealKcal, 1, null
                    );
                    // 생성된 객체를 목록에 추가
                    allMealDetails.add(detail);
                }
            } catch (Exception exception) {
                // 파일 읽기 중 예외 발생 시 스택 트레이스 출력
                exception.printStackTrace();
            }
        }

        // 모든 CSV 파일에서 읽어온 데이터를 반환
        return allMealDetails;
    }

}
