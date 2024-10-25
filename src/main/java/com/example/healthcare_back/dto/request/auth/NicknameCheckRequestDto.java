package com.example.healthcare_back.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 닉네임 중복확인 Request Body DTO

@Getter
@Setter
@NoArgsConstructor
public class NicknameCheckRequestDto {
    
    @NotBlank
    private String nickname;

}
