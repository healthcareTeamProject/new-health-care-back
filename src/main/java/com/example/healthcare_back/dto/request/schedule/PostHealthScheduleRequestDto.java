package com.example.healthcare_back.dto.request.schedule;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostHealthScheduleRequestDto {

    @NotBlank
    private String healthTitle;
    @NotBlank
    private String healthScheduleStart;
    @NotBlank
    private String healthScheduleEnd;

}
