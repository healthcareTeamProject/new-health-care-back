package com.example.healthcare_back.entity.schedule;

import java.time.LocalDateTime;

import com.example.healthcare_back.dto.request.schedule.PatchHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostHealthScheduleRequestDto;
import com.fasterxml.jackson.annotation.JsonFormat;

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
@Entity(name="healthSchedule")
@Table(name="health_Schedule")
public class HealthScheduleEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer healthScheduleNumber; // 운동 스케줄 번호

    @Column(nullable = false)
    private String userId; // 사용자 아이디

    @Column(nullable = false)
    private String healthTitle; // 일정 내용

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime healthScheduleStart; // 스케줄 시작 날짜

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime healthScheduleEnd; // 스케줄 종료 날짜

    public HealthScheduleEntity(PostHealthScheduleRequestDto dto, String userId) {
        this.userId = userId;
        this.healthTitle = dto.getHealthTitle();
        this.healthScheduleStart = dto.getHealthScheduleStart();
        this.healthScheduleEnd = dto.getHealthScheduleEnd();
    }

    public HealthScheduleEntity (PatchHealthScheduleRequestDto dto, Integer healthScheduleNumber) {
        this.healthScheduleNumber = healthScheduleNumber;
        this.healthTitle = dto.getHealthTitle();
        this.healthScheduleStart = dto.getHealthScheduleStart();
        this.healthScheduleEnd = dto.getHealthScheduleEnd();
    }

}
