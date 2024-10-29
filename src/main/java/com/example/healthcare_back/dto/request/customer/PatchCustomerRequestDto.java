package com.example.healthcare_back.dto.request.customer;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

// 회원정보 수정 Request Body DTO

@Getter
@Setter
@NoArgsConstructor
public class PatchCustomerRequestDto {
    
    @NotBlank
    private String name;
    @NotBlank
    private String nickname;
    private String profileImage;
    private String personalGoals;
    @NotNull @Column(precision = 5, scale = 1)
    private BigDecimal height;
  

}
