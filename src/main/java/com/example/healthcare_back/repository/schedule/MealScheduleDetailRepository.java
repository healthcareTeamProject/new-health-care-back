package com.example.healthcare_back.repository.schedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.schedule.MealScheduleDetailEntity;
import com.example.healthcare_back.entity.schedule.MealScheduleEntity;

@Repository
public interface MealScheduleDetailRepository extends JpaRepository<MealScheduleDetailEntity, Integer> {
    
    List<MealScheduleDetailEntity> findByMealSchedule_MealScheduleNumber(Integer mealScheduleNumber);
    List<MealScheduleDetailEntity> deleteByMealSchedule(MealScheduleEntity mealSchedule);
    List<MealScheduleDetailEntity> deleteByMealScheduleDetailNumber(Integer mealScheduleDetailNumber);

}
