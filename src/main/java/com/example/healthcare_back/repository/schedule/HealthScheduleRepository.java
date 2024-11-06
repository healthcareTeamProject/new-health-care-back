package com.example.healthcare_back.repository.schedule;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.schedule.HealthScheduleEntity;

@Repository
public interface HealthScheduleRepository extends JpaRepository<HealthScheduleEntity, Integer> {
    
    HealthScheduleEntity findByUserId(String userId);

    HealthScheduleEntity findByHealthScheduleNumber(Integer healthScheduleNumber);

    List<HealthScheduleEntity> findByHealthScheduleStartBetween(LocalDateTime start, LocalDateTime end);

}
