package com.example.healthcare_back.entity.schedule;

import java.math.BigDecimal;

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
    private BigDecimal mealKcal; // 칼로리 정보

    @Column(nullable = false)
    private Integer mealCount; // 식품 개수

    public MealScheduleDetailEntity(String mealName, BigDecimal mealKcal, Integer mealCount, MealScheduleEntity mealSchedule) {
        this.mealName = mealName;
        this.mealKcal = mealKcal;
        this.mealCount = mealCount;
        this.mealSchedule = mealSchedule;
    }
    
}
