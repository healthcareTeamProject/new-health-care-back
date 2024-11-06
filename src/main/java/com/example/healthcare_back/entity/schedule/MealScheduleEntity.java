package com.example.healthcare_back.entity.schedule;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "mealScheduleNumber", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealScheduleDetailEntity> mealDetails;

}
