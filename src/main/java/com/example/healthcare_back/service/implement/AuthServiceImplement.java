package com.example.healthcare_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.healthcare_back.common.util.AuthNumberCreator;
import com.example.healthcare_back.dto.request.auth.IdCheckRequestDto;
import com.example.healthcare_back.dto.request.auth.NicknameCheckRequestDto;
import com.example.healthcare_back.dto.request.auth.SignInRequestDto;
import com.example.healthcare_back.dto.request.auth.SignUpRequestDto;
import com.example.healthcare_back.dto.request.auth.TelAuthCheckRequestDto;
import com.example.healthcare_back.dto.request.auth.TelAuthRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.auth.SignInResponseDto;
import com.example.healthcare_back.entity.CustomerEntity;
import com.example.healthcare_back.entity.TelAuthNumberEntity;
import com.example.healthcare_back.entity.UserMuscleFatEntity;
import com.example.healthcare_back.provider.JwtProvider;
import com.example.healthcare_back.provider.SmsProvider;
import com.example.healthcare_back.repository.CustomerRepository;
import com.example.healthcare_back.repository.TelAuthNumberRepository;
import com.example.healthcare_back.repository.UserMuscleFatRepository;
import com.example.healthcare_back.repository.UserThreeMajorLiftRepository;
import com.example.healthcare_back.repository.resultSet.UserThreeMajorLiftResultSet;
import com.example.healthcare_back.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {
    
    private final SmsProvider smsProvider;
    private final JwtProvider jwtProvider;

    private final CustomerRepository customerRepository;
    private final UserMuscleFatRepository userMuscleFatRepository;
    private final UserThreeMajorLiftRepository userThreeMajorLiftRepository;
    private final TelAuthNumberRepository telAuthNumberRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public ResponseEntity<ResponseDto> telAuth(TelAuthRequestDto dto) {
        
        String telNumber = dto.getTelNumber();

        try {

            boolean isExistedTelNumber = customerRepository.existsByTelNumber(telNumber);
            if (isExistedTelNumber) return ResponseDto.duplicatedUserTelNumber();

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        String authNumber = AuthNumberCreator.number4();

        boolean isSendSuccess = smsProvider.sendMessage(telNumber, authNumber);
        if (!isSendSuccess) return ResponseDto.authenticationFail();

        try {

            TelAuthNumberEntity telAuthNumberEntity = new TelAuthNumberEntity(telNumber, authNumber);
            telAuthNumberRepository.save(telAuthNumberEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckRequestDto dto) {

        String telNumber = dto.getTelNumber();
        String authNumber = dto.getAuthNumber();

        try {

            boolean isMatched = telAuthNumberRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);
            if (!isMatched) return ResponseDto.telAuthFail();

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {

    String userId = dto.getUserId();
    String telNumber = dto.getTelNumber();
    String authNumber = dto.getAuthNumber(); 
    String password = dto.getPassword();

    try {
        // 1. 사용자 ID 중복 체크
        if (customerRepository.existsByUserId(userId)) {
            return ResponseDto.duplicatedUserId(); // 중복된 사용자 ID일 경우 응답
        }

        // 2. 전화번호 중복 체크
        if (customerRepository.existsByTelNumber(telNumber)) {
            return ResponseDto.duplicatedUserTelNumber(); // 중복된 전화번호일 경우 응답
        }

        // 3. 인증번호 확인
        if (!telAuthNumberRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber)) {
            return ResponseDto.telAuthFail(); // 인증번호 불일치일 경우 응답
        }

        // 4. 비밀번호 인코딩
        String encodedPassword = passwordEncoder.encode(password); // 비밀번호 인코딩
        dto.setPassword(encodedPassword); // 인코딩된 비밀번호 설정

        // 5. CustomerEntity 생성 및 저장
        CustomerEntity customerEntity = new CustomerEntity(dto); // Dto를 사용하여 CustomerEntity 생성
        customerRepository.save(customerEntity); // 고객 정보 저장

        if (dto.getWeight() == null) {
            throw new IllegalArgumentException("체중은 필수 입력 사항입니다.");
        }

        // UserMuscleFatEntity 생성 및 저장
        UserMuscleFatEntity userMuscleFatEntity = new UserMuscleFatEntity(dto);
        userMuscleFatEntity.setUserId(customerEntity.getUserId());
        userMuscleFatRepository.save(userMuscleFatEntity);

        // UserThreeMajorLiftResultSet 생성 및 저장
        UserThreeMajorLiftResultSet userThreeMajorLiftResultSet = new UserThreeMajorLiftResultSet(dto);
        userThreeMajorLiftResultSet.setUserId(customerEntity.getUserId());
        userThreeMajorLiftRepository.save(userThreeMajorLiftResultSet);

    } catch (IllegalArgumentException exception) {
        exception.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
        return ResponseDto.databaseError(); // 데이터베이스 에러 응답
    }

    return ResponseDto.success(); // 성공 응답
}

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        String userId = dto.getUserId();
        String password = dto.getPassword();
        String accessToken;

        try {

            CustomerEntity customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) return ResponseDto.signInFail();

            String encodedPassword = customerEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched) return ResponseDto.signInFail();

            accessToken = jwtProvider.create(userId);
            if (accessToken == null) return ResponseDto.tokenCreateFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(accessToken);
        
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {
        
        String userId = dto.getUserId();

        try {

            boolean isExistedId = customerRepository.existsByUserId(userId);
            if (isExistedId) return ResponseDto.duplicatedUserId();

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public ResponseEntity<ResponseDto> nicknameCheck(NicknameCheckRequestDto dto) {
        String nickname = dto.getNickname();

        try {

            boolean isExistedId = customerRepository.existsByNickname(nickname);
            if (isExistedId) return ResponseDto.duplicatedUserNickname();

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
 
}