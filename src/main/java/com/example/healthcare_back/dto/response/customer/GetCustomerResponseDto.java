package com.example.healthcare_back.dto.response.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.CustomerEntity;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class GetCustomerResponseDto extends ResponseDto {

    private String userId;
    private String name;
    private String nickname;
    private String telNumber;
    private String profileImage;
    private String personalGoals;
    private BigDecimal height;

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