package com.example.healthcare_back.dto.request.schedule;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostHealthScheduleRequestDto {

    @NotBlank
    private String healthTitle;
    @NotNull
    private String healthScheduleStart;
    @NotNull
    private String healthScheduleEnd;

}
