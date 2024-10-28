package com.example.healthcare_back.dto.request.auth;

import com.example.healthcare_back.dto.request.customer.PostUserMuscleFatRequestDto;
import com.example.healthcare_back.dto.request.customer.PostUserThreeMajorLiftRequestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpDataRequestDto {
    
    private SignUpRequestDto signUpRequestDto;
    private PostUserMuscleFatRequestDto postUserMuscleFatRequestDto;
    private PostUserThreeMajorLiftRequestDto postUserThreeMajorLiftRequestDto;

}
