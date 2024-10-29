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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplement implements CustomerService{

    private final CustomerRepository customerRepository;
    private final UserMuscleFatRepository userMuscleFatRepository;
    private final UserThreeMajorLiftRepository userThreeMajorLiftRepository;


    @Override
    public ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId) {
        
        CustomerEntity customerEntity = null;

        try {

            customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) return ResponseDto.authenticationFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInResponseDto.success(customerEntity);
        
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
       
        CustomerEntity customerEntity = null;

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
        UserMuscleFatEntity userMuscleFatEntity;

        try {
            // 사용자 ID로 UserMuscleFatEntity 찾기
            userMuscleFatEntity = userMuscleFatRepository.findByUserId(userId);
            if (userMuscleFatEntity == null) {
                return ResponseDto.noExistUserId(); // 사용자 ID가 존재하지 않을 때
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 처리
        }

        // 성공적으로 찾은 경우 DTO로 변환하여 응답
        return GetUserMuscleFatResponseDto.success(userMuscleFatEntity);
    }

    @Override
    public ResponseEntity<? super GetUserThreeMajorLiftResponseDto> getUserThreeMajorLift(String userId) {
        UserThreeMajorLiftEntity userThreeMajorLiftEntity;

        try {
            // 사용자 ID로 UserThreeMajorLiftEntity 찾기
            userThreeMajorLiftEntity = userThreeMajorLiftRepository.findByUserId(userId);
            if (userThreeMajorLiftEntity == null) {
                return ResponseDto.noExistUserId(); // 사용자 ID가 존재하지 않을 때
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 처리
        }

        // 성공적으로 찾은 경우 DTO로 변환하여 응답
        return GetUserThreeMajorLiftResponseDto.success(userThreeMajorLiftEntity);
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
    public ResponseEntity<ResponseDto> postUserMuscleFat(PostUserMuscleFatRequestDto dto, String userId) {
        // 1. CustomerEntity 조회
        CustomerEntity customerEntity = customerRepository.findByUserId(dto.getUserId());
        if (customerEntity == null) {
            return ResponseDto.noExistUserId(); // 사용자 미존재 에러 처리
        }

        // 2. UserMuscleFatEntity 생성
        UserMuscleFatEntity userMuscleFatEntity = new UserMuscleFatEntity(dto);

        // 3. 저장
        userMuscleFatRepository.save(userMuscleFatEntity);

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> postUserThreeMajorLift(PostUserThreeMajorLiftRequestDto dto, String userId) {
        // 1. CustomerEntity 조회
        // 전달된 UserId를 통해 데이터베이스에서 해당 사용자의 CustomerEntity를 조회합니다.
        CustomerEntity customerEntity = customerRepository.findByUserId(dto.getUserId());

        // 만약 해당 사용자가 존재하지 않으면 에러 응답을 반환합니다.
        if (customerEntity == null) {
        return ResponseDto.noExistUserId(); // 사용자 미존재 에러 처리
    }

        // 2. UserThreeMajorLiftEntity 생성
        // 요청 DTO에서 ThreeMajorLift 정보를 추출하여 UserThreeMajorLiftEntity 객체를 생성합니다.
        UserThreeMajorLiftEntity userThreeMajorLiftEntity = new UserThreeMajorLiftEntity(dto);

        // 3. 저장
        // 설정이 완료된 UserThreeMajorLiftEntity를 데이터베이스에 저장합니다.
        userThreeMajorLiftRepository.save(userThreeMajorLiftEntity);

        // 저장이 완료되면 성공 응답을 반환합니다.
        return ResponseDto.success();
    }

}