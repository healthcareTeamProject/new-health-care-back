package com.example.healthcare_back.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.healthcare_back.dto.request.auth.IdCheckRequestDto;
import com.example.healthcare_back.dto.request.auth.NicknameCheckRequestDto;
import com.example.healthcare_back.dto.request.auth.SignInRequestDto;
import com.example.healthcare_back.dto.request.auth.SignUpDataRequestDto;
import com.example.healthcare_back.dto.request.auth.SignUpRequestDto;
import com.example.healthcare_back.dto.request.auth.TelAuthCheckRequestDto;
import com.example.healthcare_back.dto.request.auth.TelAuthRequestDto;
import com.example.healthcare_back.dto.request.customer.PostUserMuscleFatRequestDto;
import com.example.healthcare_back.dto.request.customer.PostUserThreeMajorLiftRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.auth.SignInResponseDto;
import com.example.healthcare_back.service.AuthService;
import com.example.healthcare_back.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final CustomerService customerService;

    // 아이디 중복 체크
    @PostMapping("/id-check")
    public ResponseEntity<ResponseDto> idCheck(
        @RequestBody @Valid IdCheckRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.idCheck(requestBody);
        return response;
    }

    // 닉네임 중복 체크
    @PostMapping("/nickname-check")
    public ResponseEntity<ResponseDto> nicknameCheck(
        @RequestBody @Valid NicknameCheckRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.nicknameCheck(requestBody);
        return response;
    }

    // 전화번호 인증 요청
    @PostMapping("/tel-auth")
    public ResponseEntity<ResponseDto> telAuth(
        @RequestBody @Valid TelAuthRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.telAuth(requestBody);
        return response;
    }

    // 전화번호 인증 확인
    @PostMapping("/tel-auth-check")
    public ResponseEntity<ResponseDto> telAuthCheck(
        @RequestBody @Valid TelAuthCheckRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.telAuthCheck(requestBody);
        return response;
    }

    // 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto> signUp(
        @RequestBody @Valid SignUpDataRequestDto requestBody
    ) {
        SignUpRequestDto signUpRequestDto = requestBody.getSignUpRequestDto();
        PostUserMuscleFatRequestDto postUserMuscleFatRequestDto = requestBody.getPostUserMuscleFatRequestDto();
        PostUserThreeMajorLiftRequestDto postUserThreeMajorLiftRequestDto = requestBody.getPostUserThreeMajorLiftRequestDto();

        try {
        // 사용자 정보 저장
        authService.signUp(signUpRequestDto);
        // 사용자 신체 정보 저장
        customerService.postUserMuscleFat(postUserMuscleFatRequestDto);
        // 사용자 운동 기록 저장
        customerService.postUserThreeMajorLift(postUserThreeMajorLiftRequestDto);
        
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return ResponseDto.success();

    } 

    // @PostMapping("/signup")
    // public ResponseEntity<?> signUp(@RequestBody SignUpDataRequestDto signUpDataRequestDto) {
    //     authService.signUp(signUpDataRequestDto);
    //     return ResponseEntity.ok("User signed up successfully");
    // }


    // 로그인
    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
        @RequestBody @Valid SignInRequestDto requestBody
    ) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }
}
