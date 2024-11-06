package com.example.healthcare_back.repository.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.schedule.MealScheduleEntity;

@Repository
public interface MealScheduleRepository extends JpaRepository<MealScheduleEntity, Integer> {


    MealScheduleEntity findByUserId(String userId);

    MealScheduleEntity findByMealScheduleNumber(Integer mealScheduleNumber);

}
