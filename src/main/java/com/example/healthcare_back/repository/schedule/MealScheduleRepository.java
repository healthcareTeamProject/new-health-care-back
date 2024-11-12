package com.example.healthcare_back.repository.schedule;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.schedule.MealScheduleEntity;

@Repository
public interface MealScheduleRepository extends JpaRepository<MealScheduleEntity, Integer> {

    List<MealScheduleEntity> findByUserIdOrderByMealScheduleNumberDesc(String userId);

    Optional<MealScheduleEntity> findByMealScheduleNumberAndUserId(Integer mealScheduleNumber, String userId);

}
