package com.example.healthcare_back.dto.request.schedule;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchMealScheduleRequestDto {
    
    @NotBlank
    private String userId;
    @NotBlank
    private String mealTitle;
    @NotBlank
    private String mealMemo;
    @NotNull
    private LocalDateTime mealScheduleStart;
    @NotNull
    private LocalDateTime mealScheduleEnd;
    @NotNull
    private List<MealScheduleDetailRequestDto> mealDetails;

}
