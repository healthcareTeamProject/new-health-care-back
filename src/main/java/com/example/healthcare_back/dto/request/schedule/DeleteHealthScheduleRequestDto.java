package com.example.healthcare_back.dto.request.schedule;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class DeleteHealthScheduleRequestDto {

    private String userId;  // 유저 ID
    private String healthTitle;  // 운동 일정 제목
    private String healthMemo;   // 운동 일정 메모
    
    private LocalDateTime healthScheduleStart;  // 스케줄 시작일
    private LocalDateTime healthScheduleEnd;    // 스케줄 종료일
    
}
