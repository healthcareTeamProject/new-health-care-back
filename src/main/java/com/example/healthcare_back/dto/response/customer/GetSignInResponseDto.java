package com.example.healthcare_back.dto.response.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.CustomerEntity;

import lombok.Getter;

@Getter
public class GetSignInResponseDto extends ResponseDto {
    
    private String userId;
    private String name;
    private String nickname;
    

    public GetSignInResponseDto(CustomerEntity customerEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = customerEntity.getUserId();
        this.name = customerEntity.getName();
        this.nickname = customerEntity.getNickname();
    }

    public static ResponseEntity<GetSignInResponseDto> success(CustomerEntity customerEntity) {
        GetSignInResponseDto responseBody = new GetSignInResponseDto(customerEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }


}
