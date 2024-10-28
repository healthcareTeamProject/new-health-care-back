package com.example.healthcare_back.service.implement;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
import com.example.healthcare_back.entity.CustomerEntity;
import com.example.healthcare_back.entity.UserMuscleFatEntity;
import com.example.healthcare_back.entity.UserThreeMajorLiftEntity;
import com.example.healthcare_back.repository.CustomerRepository;
import com.example.healthcare_back.repository.UserMuscleFatRepository;
import com.example.healthcare_back.repository.UserThreeMajorLiftRepository;
import com.example.healthcare_back.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplement implements CustomerService{

    private final CustomerRepository customerRepository;
    private final UserMuscleFatRepository userMuscleFatRepository;
    private final UserThreeMajorLiftRepository userThreeMajorLiftRepository;

    @Override
    public ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId) {
        
        CustomerEntity customerEntity;

        try {

            customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) return ResponseDto.authenticationFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInResponseDto.success();
        
    }

    @Override
    public ResponseEntity<ResponseDto> patchCustomer(PatchCustomerRequestDto dto, String userId) {
        
        try {

            CustomerEntity customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) return ResponseDto.noExistUserId();

            customerRepository.save(customerEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<? super GetCustomerResponseDto> getCustomer(String userId) {
       
        CustomerEntity customerEntity;

        try {
            
            customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) return ResponseDto.noExistUserId();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();

        }
        
        return GetCustomerResponseDto.success(customerEntity);

    }

    @Override
    public ResponseEntity<? super GetUserMuscleFatResponseDto> getUserMuscleFat(String userId) {
        try {
            // 사용자의 신체 정보 불러오기
            List<UserMuscleFatEntity> userMuscleFatEntities = userMuscleFatRepository.findByUserId(userId);
            if (userMuscleFatEntities.isEmpty()) return ResponseDto.validationFail();

        // 결과 반환
            return GetUserMuscleFatResponseDto.success(userMuscleFatEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetUserThreeMajorLiftResponseDto> getUserThreeMajorLift(String userId) {
        try {
            // 사용자의 3대측정 정보 불러오기
            List<UserThreeMajorLiftEntity> userThreeMajorLiftEntities = userThreeMajorLiftRepository.findByUserId(userId);
            if (userThreeMajorLiftEntities.isEmpty()) return ResponseDto.validationFail();
    
            // 결과 반환
            return GetUserThreeMajorLiftResponseDto.success(userThreeMajorLiftEntities);
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetUserMuscleFatListResponseDto> getUserMuscleFatList() {

        List<UserMuscleFatEntity> userMuscleFatEntities = new ArrayList<>();

        try {

            userMuscleFatEntities = userMuscleFatRepository.findByOrderByUserMuscleFatNumberDesc();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserMuscleFatListResponseDto.success(userMuscleFatEntities);
    }

    @Override
    public ResponseEntity<? super GetUserThreeMajorLiftListResponseDto> getUserThreeMajorLiftList() {

        List<UserThreeMajorLiftEntity> userThreeMajorLiftEntities = new ArrayList<>();

        try {

            userThreeMajorLiftEntities = userThreeMajorLiftRepository.findByOrderByUserThreeMajorLiftNumberDesc();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserThreeMajorLiftListResponseDto.success(userThreeMajorLiftEntities);
    }

    @Override
    public ResponseEntity<ResponseDto> signUpUserMuscleFat(@Valid PostUserMuscleFatRequestDto dto) {
        Double weight = dto.getWeight();
        Double skeletalMuscleMass = dto.getSkeletalMuscleMass();
        Double bodyFatMass = dto.getBodyFatMass();

        // 유효성 검사: 체중, 골격근량, 체지방량이 음수인지 확인
        if (weight < 0 || skeletalMuscleMass < 0 || bodyFatMass < 0) {
            return ResponseDto.validationFail();
        }

        try {
            // UserMuscleFatEntity 객체 생성 및 등록 날짜 설정
            UserMuscleFatEntity userMuscleFatEntity = new UserMuscleFatEntity();
            
            // UserMuscleFatEntity에 등록 번호는 JPA가 자동으로 생성
            userMuscleFatRepository.save(userMuscleFatEntity);

            // 성공 시 등록 번호와 등록 날짜를 포함한 응답 생성
            return ResponseDto.success(userMuscleFatEntity.getUserMuscleFatNumber(), userMuscleFatEntity.getUserMuscleFatDate());
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> signUpUserThreeMajorLift(@Valid PostUserThreeMajorLiftRequestDto dto) {

        Double deadlift = dto.getDeadlift();
        Double benchPress = dto.getBenchPress();
        Double squat = dto.getSquat();

        // 유효성 검사: 데드리프트, 벤치프레스, 스쿼트가 음수인지 확인
        if (deadlift < 0 || benchPress < 0 || squat < 0) {
            return ResponseDto.validationFail();
        }

        try {

            // UserThreeMajorLiftEntity 객체 생성 및 등록 날짜 설정
            UserThreeMajorLiftEntity userThreeMajorLiftEntity = new UserThreeMajorLiftEntity();
            
            // UserThreeMajorLiftEntity에 등록 번호는 JPA가 자동으로 생성
            userThreeMajorLiftRepository.save(userThreeMajorLiftEntity);

            // 성공 시 등록 번호와 등록 날짜를 포함한 응답 생성
            return ResponseDto.success(userThreeMajorLiftEntity.getThreeMajorLiftNumber(), userThreeMajorLiftEntity.getThreeMajorLiftDate());

        }   catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
}