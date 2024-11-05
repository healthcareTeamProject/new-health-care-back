package com.example.healthcare_back.dto.request.schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchHealthScheduleRequestDto {
    
    private String userId;  // 유저 ID
    private String healthTitle;  // 운동 일정 제목
    private String healthMemo;   // 운동 일정 메모
    
}