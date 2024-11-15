package com.example.healthcare_back.entity.schedule;

import java.math.BigDecimal;

import com.example.healthcare_back.common.util.CustomBigDecimalSerializer;
import com.example.healthcare_back.dto.request.schedule.PatchMealScheduleRequestDto.MealDetail;
import com.example.healthcare_back.dto.request.schedule.PostMealScheduleRequestDto;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="mealScheduleDetail")
@Table(name="meal_Schedule_Detail")
public class MealScheduleDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mealScheduleDetailNumber; // 식품 정보 번호

    @ManyToOne
    @JoinColumn(name = "mealScheduleNumber", nullable = false)
    private MealScheduleEntity mealSchedule; // 외래 키로 연결된 식단 스케줄

    @Column(nullable = false)
    private String mealName; // 식품 이름

    @Column(nullable = false)
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal mealKcal; // 칼로리 정보

    @Column(nullable = false)
    private Integer mealCount; // 식품 개수

    // 생성자
    public MealScheduleDetailEntity(MealDetail detail, String mealName, BigDecimal mealKcal, Integer mealCount, MealScheduleEntity mealSchedule) {
        this.mealName = detail.getMealName();
        this.mealKcal = detail.getMealKcal();
        this.mealCount = detail.getMealCount();
        this.mealSchedule = mealSchedule;
    }
}
