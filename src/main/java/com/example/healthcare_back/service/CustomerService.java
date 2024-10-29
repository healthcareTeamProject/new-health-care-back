package com.example.healthcare_back.service;

import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.request.customer.PatchCustomerRequestDto;
import com.example.healthcare_back.dto.request.customer.PostUserMuscleFatRequestDto;
import com.example.healthcare_back.dto.request.customer.PostUserThreeMajorLiftRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.customer.GetCustomerResponseDto;
import com.example.healthcare_back.dto.response.customer.GetSignInResponseDto;
import com.example.healthcare_back.dto.response.customer.GetUserMuscleFatListResponseDto;
import com.example.healthcare_back.dto.response.customer.GetUserMuscleFatResponseDto;
import com.example.healthcare_back.dto.response.customer.GetUserThreeMajorLiftListResponseDto;
import com.example.healthcare_back.dto.response.customer.GetUserThreeMajorLiftResponseDto;

public interface CustomerService {

    ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId);
    ResponseEntity<? super GetCustomerResponseDto> getCustomer(String userId);
    ResponseEntity<? super GetUserMuscleFatResponseDto> getUserMuscleFat(String userId);
    ResponseEntity<? super GetUserMuscleFatListResponseDto> getUserMuscleFatList();
    ResponseEntity<? super GetUserThreeMajorLiftResponseDto> getUserThreeMajorLift(String userId);
    ResponseEntity<? super GetUserThreeMajorLiftListResponseDto> getUserThreeMajorLiftList();

    ResponseEntity<ResponseDto> patchCustomer(PatchCustomerRequestDto dto, String userId);
    ResponseEntity<ResponseDto> postUserMuscleFat(PostUserMuscleFatRequestDto dto, String userId);
    ResponseEntity<ResponseDto> postUserThreeMajorLift(PostUserThreeMajorLiftRequestDto dto, String userId);
}