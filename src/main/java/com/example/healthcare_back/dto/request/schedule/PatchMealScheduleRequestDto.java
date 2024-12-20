package com.example.healthcare_back.dto.request.schedule;
import java.math.BigDecimal;
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
    private String mealTitle;

    @NotNull
    private String mealScheduleStart;

    @NotNull
    private String mealScheduleEnd;

    @NotNull
    private List<MealDetail> mealMemo; // 변경된 부분: mealMemo를 List<MealDetail>로 수정

    @Getter
    @Setter
    public static class MealDetail {
        @NotBlank
        private String mealName;
        
        @NotNull
        private BigDecimal mealKcal;
        
        @NotNull
        private Integer mealCount;
    }

}

