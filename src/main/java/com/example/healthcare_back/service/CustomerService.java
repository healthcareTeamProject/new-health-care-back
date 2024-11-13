package com.example.healthcare_back.service;


import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.request.customer.PatchCustomerRequestDto;
import com.example.healthcare_back.dto.request.customer.PatchUserMuscleFatRequestDto;
import com.example.healthcare_back.dto.request.customer.PatchUserThreeMajorLiftRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.customer.GetCustomerResponseDto;
import com.example.healthcare_back.dto.response.customer.GetSignInResponseDto;
import com.example.healthcare_back.dto.response.customer.GetUserMuscleFatListResponseDto;
import com.example.healthcare_back.dto.response.customer.GetUserThreeMajorLiftListResponseDto;

public interface CustomerService {

    // 로그인 사용자 정보 조회
    ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId);

    // 사용자 세부 정보 조회
    ResponseEntity<? super GetCustomerResponseDto> getCustomer(String userId);

    // 사용자 신체정보 조회
    ResponseEntity<? super GetUserMuscleFatListResponseDto> getUserMuscleFatList(String userId);

    // 사용자 3대측정 정보 조회
    ResponseEntity<? super GetUserThreeMajorLiftListResponseDto> getUserThreeMajorLiftList(String userId);

    // 사용자 정보 수정
    ResponseEntity<ResponseDto> patchCustomer(PatchCustomerRequestDto dto, String userId);

    // 사용자 신체정보 수정
    ResponseEntity<ResponseDto> patchUserMuscleFatCustomer(PatchUserMuscleFatRequestDto dto, String userId);

    // 사용자 3대측정 정보 수정
    ResponseEntity<ResponseDto> patchThreeMajorLiftCustomer(PatchUserThreeMajorLiftRequestDto dto, String userId);
}