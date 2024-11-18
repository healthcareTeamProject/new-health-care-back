package com.example.healthcare_back.repository.schedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.schedule.HealthScheduleEntity;

@Repository
public interface HealthScheduleRepository extends JpaRepository<HealthScheduleEntity, Integer> {
    List<HealthScheduleEntity> findByUserIdOrderByHealthScheduleNumberDesc(String userId);
    List<HealthScheduleEntity> findByHealthScheduleNumber(Integer healthScheduleNumber);
    List<HealthScheduleEntity> deleteByHealthScheduleNumber(Integer healthScheduleNumber);
    HealthScheduleEntity findByHealthScheduleNumberAndUserId(Integer healthScheduleNumber, String userId);

    // 특정 날짜와 사용자에 대한 운동 일정 개수를 반환
    @Query("SELECT COUNT(h) FROM healthSchedule h WHERE h.healthScheduleStart = :date AND h.userId = :userId")
    Integer countByHealthScheduleStartAndUserId(@Param("date") String date, @Param("userId") String userId);
}
