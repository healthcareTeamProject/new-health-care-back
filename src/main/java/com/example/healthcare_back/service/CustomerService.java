package com.example.healthcare_back.service;

import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.request.auth.SignUpUserMuscleFatRequestDto;
import com.example.healthcare_back.dto.request.auth.SignUpUserThreeMajorLiftRequestDto;
import com.example.healthcare_back.dto.request.customer.PostUserMuscleFatRequestDto;
import com.example.healthcare_back.dto.request.customer.PostUserThreeMajorLiftRequestDto;
import com.example.healthcare_back.dto.request.customer.PatchCustomerRequestDto;
import com.example.healthcare_back.dto.request.customer.PatchUserMuscleFatRequestDto;
import com.example.healthcare_back.dto.request.customer.PatchUserThreeMajorLiftRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.customer.GetCustomerResponseDto;
import com.example.healthcare_back.dto.response.customer.GetSignInResponseDto;
import com.example.healthcare_back.dto.response.customer.GetUserMuscleFatListResponseDto;
import com.example.healthcare_back.dto.response.customer.GetUserThreeMajorLiftListResponseDto;

public interface CustomerService {

    ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId);
    ResponseEntity<? super GetCustomerResponseDto> getCustomer(String userId);
    ResponseEntity<? super GetUserMuscleFatListResponseDto> getUserMuscleFatList(String userId);
    ResponseEntity<? super GetUserThreeMajorLiftListResponseDto> getUserThreeMajorLiftList(String userId);

    ResponseEntity<ResponseDto> patchCustomer(PatchCustomerRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchUserMuscleFatCustomer(PatchUserMuscleFatRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchThreeMajorLiftCustomer(PatchUserThreeMajorLiftRequestDto dto, String userId);
    ResponseEntity<ResponseDto> signUpUserMuscleFat(SignUpUserMuscleFatRequestDto dto, String userId);
    ResponseEntity<ResponseDto> signUpUserThreeMajorLift(SignUpUserThreeMajorLiftRequestDto dto, String userId);
    ResponseEntity<ResponseDto> postUserMuscleFat(PostUserMuscleFatRequestDto dto, String userId);
    ResponseEntity<ResponseDto> postUserThreeMajorLift(PostUserThreeMajorLiftRequestDto dto, String userId);

}