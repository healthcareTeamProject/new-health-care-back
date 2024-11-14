package com.example.healthcare_back.dto.response.customer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetCustomerListResponseDto extends ResponseDto {

    private List<GetCustomerResponseDto> customerList;

    private GetCustomerListResponseDto(List<GetCustomerResponseDto> customerResponseList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.customerList = customerResponseList;
    }

    public static ResponseEntity<GetCustomerListResponseDto> success(List<GetCustomerResponseDto> customerResponseList) {
        GetCustomerListResponseDto responseBody = new GetCustomerListResponseDto(customerResponseList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
