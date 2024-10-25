package com.example.healthcare_back.service;

import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.request.auth.IdCheckRequestDto;
import com.example.healthcare_back.dto.request.auth.NicknameCheckRequestDto;
import com.example.healthcare_back.dto.request.auth.SignInRequestDto;
import com.example.healthcare_back.dto.request.auth.SignUpRequestDto;
import com.example.healthcare_back.dto.request.auth.TelAuthCheckRequestDto;
import com.example.healthcare_back.dto.request.auth.TelAuthRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.auth.SignInResponseDto;

public interface AuthService {
    
   /**
    * 주어진 사용자 ID가 이미 사용 중인지 확인합니다.
    *
    * @param requestBody 사용자 ID를 포함하는 요청 객체
    * @return ResponseEntity<ResponseDto> 중복 여부 결과를 포함하는 응답 객체
    */
   ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto requestBody);

   /**
    * 주어진 닉네임이 이미 사용 중인지 확인합니다.
    *
    * @param requestBody 닉네임을 포함하는 요청 객체
    * @return ResponseEntity<ResponseDto> 중복 여부 결과를 포함하는 응답 객체
    */
   ResponseEntity<ResponseDto> nicknameCheck(NicknameCheckRequestDto requestBody);

   /**
    * 전화 인증 과정을 시작하고 SMS를 전송합니다.
    *
    * @param requestBody 인증할 전화번호를 포함하는 요청 객체
    * @return ResponseEntity<ResponseDto> 인증 과정의 결과를 포함하는 응답 객체
    */
   ResponseEntity<ResponseDto> telAuth(TelAuthRequestDto requestBody);

   /**
    * 사용자가 제공한 전화 인증 번호의 유효성을 검사합니다.
    *
    * @param requestBody 전화번호와 인증 번호를 포함하는 요청 객체
    * @return ResponseEntity<ResponseDto> 인증 확인 결과를 포함하는 응답 객체
    */
   ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckRequestDto requestBody);

   /**
    * 주어진 회원가입 정보를 사용하여 새로운 사용자를 등록합니다.
    *
    * @param requestBody 회원가입 정보를 포함하는 요청 객체
    * @return ResponseEntity<ResponseDto> 회원가입 작업의 결과를 포함하는 응답 객체
    */
   ResponseEntity<ResponseDto> signUp(SignUpRequestDto requestBody);

   /**
    * 사용자의 로그인 정보를 기반으로 인증합니다.
    *
    * @param requestBody 로그인 정보를 포함하는 요청 객체
    * @return ResponseEntity<SignInResponseDto> 로그인 작업의 결과를 포함하는 응답 객체
    */
   ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto requestBody);

   

}
