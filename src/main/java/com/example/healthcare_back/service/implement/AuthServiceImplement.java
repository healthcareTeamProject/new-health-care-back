package com.example.healthcare_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.healthcare_back.common.util.AuthNumberCreator;
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
import com.example.healthcare_back.entity.CustomerEntity;
import com.example.healthcare_back.entity.TelAuthNumberEntity;
import com.example.healthcare_back.entity.UserMuscleFatEntity;
import com.example.healthcare_back.entity.UserThreeMajorLiftEntity;
import com.example.healthcare_back.provider.JwtProvider;
import com.example.healthcare_back.provider.SmsProvider;
import com.example.healthcare_back.repository.CustomerRepository;
import com.example.healthcare_back.repository.TelAuthNumberRepository;
import com.example.healthcare_back.repository.UserMuscleFatRepository;
import com.example.healthcare_back.repository.UserThreeMajorLiftRepository;
import com.example.healthcare_back.service.AuthService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

    @Transactional
    @Override
    public ResponseEntity<ResponseDto> signUp(@Valid @RequestBody SignUpDataRequestDto signUpDataRequestDto) {

        // Dto에서 필요한 정보 가져오기
        SignUpRequestDto signUpRequestDto = signUpDataRequestDto.getSignUpRequestDto();
        PostUserMuscleFatRequestDto postUserMuscleFatRequestDto = signUpDataRequestDto.getPostUserMuscleFatRequestDto();
        PostUserThreeMajorLiftRequestDto postUserThreeMajorLiftRequestDto = signUpDataRequestDto.getPostUserThreeMajorLiftRequestDto();

        String userId = signUpRequestDto.getUserId();
        String telNumber = signUpRequestDto.getTelNumber();
        String authNumber = signUpRequestDto.getAuthNumber();
        String password = signUpRequestDto.getPassword();
    
        try {
            // 1. 사용자 ID 중복 체크
            if (customerRepository.existsByUserId(userId)) {
                return ResponseDto.duplicatedUserId();
            }
    
            // 2. 전화번호 중복 체크
            if (customerRepository.existsByTelNumber(telNumber)) {
                return ResponseDto.duplicatedUserTelNumber();
            }
    
            // 3. 인증번호 확인
            if (!telAuthNumberRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber)) {
                return ResponseDto.telAuthFail();
            }
    
            // 4. 비밀번호 인코딩
            String encodedPassword = passwordEncoder.encode(password);
            signUpRequestDto.setPassword(encodedPassword);
    
            // 5. CustomerEntity 생성 및 저장
            CustomerEntity customerEntity = new CustomerEntity(signUpRequestDto); // Dto로 엔티티 생성자 호출
            customerRepository.save(customerEntity);
    
            // 6. UserThreeMajorLiftEntity 생성 및 저장
            if (postUserThreeMajorLiftRequestDto != null) {
                UserThreeMajorLiftEntity userThreeMajorLiftEntity = new UserThreeMajorLiftEntity(postUserThreeMajorLiftRequestDto);
                userThreeMajorLiftEntity.setCustomerEntity(customerEntity); // 연관 관계 설정
                userThreeMajorLiftRepository.save(userThreeMajorLiftEntity);
            }
    
            // 7. UserMuscleFatEntity 생성 및 저장
            if (postUserMuscleFatRequestDto != null) {
                UserMuscleFatEntity userMuscleFatEntity = new UserMuscleFatEntity(postUserMuscleFatRequestDto);
                userMuscleFatEntity.setCustomerEntity(customerEntity); // 연관 관계 설정
                userMuscleFatRepository.save(userMuscleFatEntity);
            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 에러 응답
        }
    
        return ResponseDto.success(); // 성공 응답
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

}