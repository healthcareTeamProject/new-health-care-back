package com.example.healthcare_back.dto.request.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchHealthScheduleRequestDto {

    @NotBlank
    private String healthTitle; // 수정할 운동 일정 내용
    @NotNull
    private String healthScheduleStart; // 수정할 시작 날짜
    @NotNull
    private String healthScheduleEnd; // 수정할 종료 날짜

}