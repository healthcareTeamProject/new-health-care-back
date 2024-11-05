package com.example.healthcare_back.repository.schedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.schedule.MealScheduleDetailEntity;

@Repository
public interface MealScheduleDetailRepository extends JpaRepository<MealScheduleDetailEntity, Integer> {
    List<MealScheduleDetailEntity> findByMealScheduleNumber(int mealScheduleNumber);
}
