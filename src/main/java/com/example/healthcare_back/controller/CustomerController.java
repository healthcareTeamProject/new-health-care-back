package com.example.healthcare_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.healthcare_back.dto.request.customer.PatchCustomerRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.customer.GetCustomerResponseDto;
import com.example.healthcare_back.dto.response.customer.GetSignInResponseDto;
import com.example.healthcare_back.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    /**
     * 로그인한 고객의 정보를 가져옵니다.
     * 
     * @param userId 인증된 사용자의 ID
     * @return 고객의 로그인 정보
     */
    @GetMapping(value = {"", "/"})
    public ResponseEntity<? super GetSignInResponseDto> getSignIn(
        @AuthenticationPrincipal String userId
    ) {
        return customerService.getSignIn(userId);
    }

    // /**
    //  * 고객의 기본 정보를 가져옵니다.
    //  * 
    //  * @param userId 고객의 ID
    //  * @return 고객의 정보
    //  */
    // @GetMapping("/{userId}")
    // public ResponseEntity<? super GetCustomerResponseDto> getCustomer(
    //     @PathVariable("userId") String userId
    // ) {
    //     return customerService.getCustomer(userId);
    // }

    // /**
    //  * 고객의 신체 정보 리스트를 가져옵니다.
    //  * 
    //  * @param userId 고객의 ID
    //  * @return 고객의 정보
    //  */

    // @GetMapping("/{userId}/user-muscle-fat")
    // public ResponseEntity<? super GetCustomerResponseDto> getCustomer(
    //     @PathVariable("userId") String userId
    // ) {
    //     return customerService.getCustomer(userId);
    // }

    // /**
    //  * 고객의 3대 측정 정보 리스트를 가져옵니다.
    //  * 
    //  * @param userId 고객의 ID
    //  * @return 고객의 정보
    //  */
    // @GetMapping("/{userId}/user-three-major-lift")
    // public ResponseEntity<? super GetCustomerResponseDto> getCustomer(
    //     @PathVariable("userId") String userId
    // ) {
    //     return customerService.getCustomer(userId);
    // }

    /**
     * 고객 정보를 업데이트합니다.
     * 
     * @param requestBody 업데이트할 고객 정보
     * @param userId 인증된 사용자의 ID
     * @return 업데이트 결과
     */
    @PatchMapping(value = {"", " "})
    public ResponseEntity<ResponseDto> patchCustomer(
        @RequestBody @Valid PatchCustomerRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ) {
        return customerService.patchCustomer(requestBody, userId);
    }
}