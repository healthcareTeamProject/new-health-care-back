package com.example.healthcare_back.entity;

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
public class MealScheduleDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mealScheduleDetailNumber;

    @Column(nullable = false)
    private Integer mealScheduleNumber;

    @Column(nullable = false, length = 30)
    private String mealName;

    @Column(nullable = false, precision = 5, scale = 1)
    private Double mealKcal;

    @Column(nullable = false)
    private Integer mealCount;
}
