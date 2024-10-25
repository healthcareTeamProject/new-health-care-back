package com.example.healthcare_back.dto.request.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostUserThreeMajorLiftRequestDto {
    
    private Double deadlift;
    private Double benchPress;
    private Double squat;

}
