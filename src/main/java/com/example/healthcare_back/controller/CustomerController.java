package com.example.healthcare_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.healthcare_back.dto.request.customer.PatchCustomerRequestDto;
import com.example.healthcare_back.dto.request.customer.PatchUserMuscleFatRequestDto;
import com.example.healthcare_back.dto.request.customer.PatchUserThreeMajorLiftRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.customer.GetCustomerResponseDto;
import com.example.healthcare_back.dto.response.customer.GetSignInResponseDto;
import com.example.healthcare_back.dto.response.customer.GetUserMuscleFatListResponseDto;
import com.example.healthcare_back.dto.response.customer.GetUserThreeMajorLiftListResponseDto;
import com.example.healthcare_back.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // 로그인한 고객의 기본 정보를 가져옵니다.
    @GetMapping("/sign-in")
    public ResponseEntity<? super GetSignInResponseDto> getSignIn(
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetSignInResponseDto> response = customerService.getSignIn(userId);
        return response;
    }

    // 고객의 기본 정보를 가져옵니다.
    @GetMapping("/{userId}")
    public ResponseEntity<? super GetCustomerResponseDto> getCustomer(
        @AuthenticationPrincipal String userId,
        @AuthenticationPrincipal String requestedUserId
    ) {
        return customerService.getCustomer(userId, requestedUserId);
    }

    // 고객의 신체 정보 리스트를 가져옵니다.
    @GetMapping("/{userId}/user-muscle-fat-list")
    public ResponseEntity<? super GetUserMuscleFatListResponseDto> getUserMuscleFatList(
        @AuthenticationPrincipal String userId,
        @AuthenticationPrincipal String requestedUserId
    ) {
        ResponseEntity<? super GetUserMuscleFatListResponseDto> response = customerService.getUserMuscleFatList(userId, requestedUserId);
        return response;
    }

    // 고객의 3대 측정 정보 리스트를 가져옵니다.
    @GetMapping("/{userId}/user-three-major-lift-list")
    public ResponseEntity<? super GetUserThreeMajorLiftListResponseDto> getUserThreeMajorLift(
        @AuthenticationPrincipal String userId,
        @AuthenticationPrincipal String requestedUserId
    ) {
        ResponseEntity<? super GetUserThreeMajorLiftListResponseDto> response = customerService.getUserThreeMajorLiftList(userId, requestedUserId);
        return response;
    }

    // 고객 정보를 업데이트합니다.
    @PatchMapping(value = {"", " "})
    public ResponseEntity<ResponseDto> patchCustomer(
        @RequestBody @Valid PatchCustomerRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = customerService.patchCustomer(requestBody, userId);
        return response;
    }

    // 고객의 신체 정보를 업데이트합니다.
    @PatchMapping("/{userId}/user-muscle-fat")
        public ResponseEntity<ResponseDto> patchUserMuscleFatCustomer(
        @RequestBody @Valid PatchUserMuscleFatRequestDto requestBody,
        @AuthenticationPrincipal String userId,
        @AuthenticationPrincipal String requestedUserId
        
    ) {
        ResponseEntity<ResponseDto> response = customerService.patchUserMuscleFatCustomer(requestBody, userId, requestedUserId);
        return response;
    }

    // 고객의 3대 측정 정보를 업데이트합니다.
    @PatchMapping("/{userId}/user-three-major-lift")
    public ResponseEntity<ResponseDto> patchThreeMajorLiftCustomer(
        @RequestBody @Valid PatchUserThreeMajorLiftRequestDto requestBody,
        @AuthenticationPrincipal String userId,
        @AuthenticationPrincipal String requestedUserId
    ) {
        ResponseEntity<ResponseDto> response = customerService.patchThreeMajorLiftCustomer(requestBody, userId, requestedUserId);
        return response;
    }
    
}