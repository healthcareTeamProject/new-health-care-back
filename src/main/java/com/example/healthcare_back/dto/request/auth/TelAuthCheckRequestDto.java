package com.example.healthcare_back.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 인증번호 확인 Request Body DTO

@Getter
@Setter
@NoArgsConstructor
public class TelAuthCheckRequestDto {

    @NotBlank
    private String telNumber;
    @NotBlank
    private String authNumber;
    
}
