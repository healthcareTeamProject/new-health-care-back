package com.example.healthcare_back.entity;

import java.time.LocalDateTime;

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
    private Integer healthScheduleNumber;

    @Column(nullable = false, length = 20)
    private String userId;

    @Column(nullable = false, length = 20)
    private String healthTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String healthMemo;

    @Column(nullable = false)
    private LocalDateTime healthScheduleStart;

    @Column(nullable = false)
    private LocalDateTime healthScheduleEnd;

}
