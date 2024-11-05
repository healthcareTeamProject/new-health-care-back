package com.example.healthcare_back.dto.request.schedule;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchHealthScheduleRequestDto {
    
    @NotBlank
    String userId;
    @NotBlank
    String title;
    @NotBlank
    String memo;
    
}
