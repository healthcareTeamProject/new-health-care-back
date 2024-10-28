package com.example.healthcare_back.dto.request.auth;

import org.hibernate.validator.constraints.Length;

import com.example.healthcare_back.dto.request.customer.PostUserMuscleFatRequestDto;
import com.example.healthcare_back.dto.request.customer.PostUserThreeMajorLiftRequestDto;


import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {
    
    @NotBlank
    @Length(max=15)
    private String name;
    @NotBlank
    @Length(max=30)
    private String nickname;
    @NotBlank
    @Length(max=20)
    private String userId;
    @NotBlank
    @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[0-9]).{8,13}$")
    private String password;
    @NotBlank
    @Pattern(regexp="^[0-9]{11}$")
    private String telNumber;
    @NotBlank
    private String authNumber;
    @NotBlank
    @Pattern(regexp="^(home|kakao|naver)$")
    private String joinPath;
    private String snsId;
    @NotNull 
    private BigDecimal height;
    private String profileImage;
    private String personalGoals;

    private PostUserMuscleFatRequestDto postUserMuscleFatRequestDto;
    private PostUserThreeMajorLiftRequestDto postUserThreeMajorLiftRequestDto;

}
