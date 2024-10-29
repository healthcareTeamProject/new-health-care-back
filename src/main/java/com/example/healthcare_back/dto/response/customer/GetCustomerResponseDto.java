package com.example.healthcare_back.dto.response.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.CustomerEntity;

import java.math.BigDecimal;
import lombok.Getter;

// 회원 정보 불러오기 Response DTO

@Getter
public class GetCustomerResponseDto extends ResponseDto {

    private final String userId;
    private final String name;
    private final String nickname;
    private final String telNumber;
    private final String profileImage;
    private final String personalGoals;
    private final BigDecimal height;

    private GetCustomerResponseDto(CustomerEntity customerEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = customerEntity.getUserId();
        this.name = customerEntity.getName();
        this.nickname = customerEntity.getNickname();
        this.telNumber = customerEntity.getTelNumber();
        this.profileImage = customerEntity.getProfileImage();
        this.personalGoals = customerEntity.getPersonalGoals();
        this.height = customerEntity.getHeight();
    }

    public static ResponseEntity<GetCustomerResponseDto> success(CustomerEntity customerEntity) {
        GetCustomerResponseDto responseBody = new GetCustomerResponseDto(customerEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}