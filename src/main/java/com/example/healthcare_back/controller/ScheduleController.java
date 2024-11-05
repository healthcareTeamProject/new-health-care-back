package com.example.healthcare_back.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.healthcare_back.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    
    private final ScheduleService scheduleService;

}
