package com.example.healthcare_back.repository.schedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.schedule.MealScheduleEntity;

@Repository
public interface MealScheduleRepository extends JpaRepository<MealScheduleEntity, Integer> {
    List<MealScheduleEntity> findByUserId(String userId);
}
