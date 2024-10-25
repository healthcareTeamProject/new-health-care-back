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
import com.example.healthcare_back.provider.JwtProvider;
import com.example.healthcare_back.provider.SmsProvider;
import com.example.healthcare_back.repository.CustomerRepository;
import com.example.healthcare_back.repository.TelAuthNumberRepository;
import com.example.healthcare_back.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {
    
    private final SmsProvider smsProvider;
    private final JwtProvider jwtProvider;

    private final CustomerRepository customerRepository;
    private final TelAuthNumberRepository telAuthNumberRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
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
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {

        String userId = dto.getUserId();
        String telNumber = dto.getTelNumber();
        String authNumber = dto.getAuthNumber();
        String password = dto.getPassword();

        try {

            boolean isExistedId = customerRepository.existsByUserId(userId);
            if (isExistedId) return ResponseDto.duplicatedUserId();

            boolean isExistedTelNumber = customerRepository.existsByTelNumber(telNumber);
            if (isExistedTelNumber) return ResponseDto.duplicatedUserTelNumber();
            
            boolean isMatched = telAuthNumberRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);
            if (!isMatched) return ResponseDto.telAuthFail();

            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            CustomerEntity customerEntity = new CustomerEntity(dto);
            customerRepository.save(customerEntity);

            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        String userId = dto.getUserId();
        String password = dto.getPassword();

        String accessToken = null;

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