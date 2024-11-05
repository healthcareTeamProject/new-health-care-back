package com.example.healthcare_back.entity;


import java.time.LocalDateTime;

import com.example.healthcare_back.dto.request.schedule.PatchMealScheduleDetailRequestDto;
import com.example.healthcare_back.dto.response.schedule.GetMealScheduleDetailRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="mealSchedule")
@Table(name="meal_Schedule")
public class MealScheduleEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mealScheduleNumber;

    @Column(nullable = false, length = 20)
    private String userId;

    @Column(nullable = false, length = 20)
    private String mealTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mealMemo;

    @Column(nullable = false)
    private LocalDateTime mealScheduleStart;

    @Column(nullable = false)
    private LocalDateTime mealScheduleEnd;


    public void PostMealScheduleDetail(GetMealScheduleDetailRequestDto dto) {
        
    }

    public void PatchMealScheduleDetail(PatchMealScheduleDetailRequestDto dto) {

    }
}
