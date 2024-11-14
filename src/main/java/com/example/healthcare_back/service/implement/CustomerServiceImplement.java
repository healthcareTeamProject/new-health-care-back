package com.example.healthcare_back.service.implement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.healthcare_back.dto.request.customer.PatchCustomerRequestDto;
import com.example.healthcare_back.dto.request.customer.PatchUserMuscleFatRequestDto;
import com.example.healthcare_back.dto.request.customer.PatchUserThreeMajorLiftRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.customer.GetCustomerListResponseDto;
import com.example.healthcare_back.dto.response.customer.GetCustomerResponseDto;
import com.example.healthcare_back.dto.response.customer.GetSignInResponseDto;
import com.example.healthcare_back.dto.response.customer.GetUserMuscleFatListResponseDto;
import com.example.healthcare_back.dto.response.customer.GetUserThreeMajorLiftListResponseDto;
import com.example.healthcare_back.entity.customer.CustomerEntity;
import com.example.healthcare_back.entity.customer.UserMuscleFatEntity;
import com.example.healthcare_back.entity.customer.UserThreeMajorLiftEntity;
import com.example.healthcare_back.repository.customer.CustomerRepository;
import com.example.healthcare_back.repository.customer.UserMuscleFatRepository;
import com.example.healthcare_back.repository.customer.UserThreeMajorLiftRepository;
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
            if (customerEntity == null) return ResponseDto.noExistUserId();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInResponseDto.success(customerEntity);
        
    }

    
    @Override
    public ResponseEntity<? super GetCustomerResponseDto> getCustomer(String userId, String requestedUserId) {
       
        CustomerEntity customerEntity = null;

        if (!userId.equals(requestedUserId)) {
            return ResponseDto.noExistUserId(); // 권한이 없다는 응답
        }

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
    public ResponseEntity<? super GetUserMuscleFatListResponseDto> getUserMuscleFatList(String userId, String requestedUserId) {
        
        // 요청한 userId와 인증된 userId가 다른 경우 권한 없음 응답
        if (!userId.equals(requestedUserId)) {
            return ResponseDto.noExistUserId(); // 권한이 없다는 응답
        }
    
        List<UserMuscleFatEntity> userMuscleFatEntities;
    
        try {
            userMuscleFatEntities = userMuscleFatRepository.findByUserIdOrderByUserMuscleFatNumberAsc(requestedUserId);
            if (userMuscleFatEntities.isEmpty()) {
                userMuscleFatEntities = new ArrayList<>();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    
        return GetUserMuscleFatListResponseDto.success(userMuscleFatEntities);
    }
    
    @Override
    public ResponseEntity<? super GetUserThreeMajorLiftListResponseDto> getUserThreeMajorLiftList(String userId, String requestedUserId) {

        // 요청한 userId와 인증된 userId가 다른 경우 권한 없음 응답
        if (!userId.equals(requestedUserId)) {
            return ResponseDto.noExistUserId(); // 권한이 없다는 응답
        }

        List<UserThreeMajorLiftEntity> userThreeMajorLiftEntities;

        try {
            // 추가 로깅과 예외 관리 기능
            userThreeMajorLiftEntities = userThreeMajorLiftRepository.findByUserIdOrderByUserThreeMajorLiftNumberAsc(requestedUserId);
            if (userThreeMajorLiftEntities.isEmpty()) {
                userThreeMajorLiftEntities = new ArrayList<>();
            }

        } catch (Exception exception) {
            exception.printStackTrace(); // 예외 로깅

            return ResponseDto.databaseError();
        }

        return GetUserThreeMajorLiftListResponseDto.success(userThreeMajorLiftEntities);
    }

    @Override
    public ResponseEntity<ResponseDto> patchCustomer(PatchCustomerRequestDto dto, String userId) {

        try {

            // 사용자 존재 여부 확인
            CustomerEntity customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) {
                return ResponseDto.noExistUserId(); // 사용자 ID가 존재하지 않음을 알리는 응답
            }

            customerEntity.setName(dto.getName());
            customerEntity.setNickname(dto.getNickname());
            customerEntity.setProfileImage(dto.getProfileImage());
            customerEntity.setPersonalGoals(dto.getPersonalGoals());
            customerEntity.setHeight(dto.getHeight());
            customerRepository.save(customerEntity);
                
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }


    @Override
    public ResponseEntity<ResponseDto> patchUserMuscleFatCustomer(PatchUserMuscleFatRequestDto dto, String userId, String requestedUserId) {

        // 요청한 userId와 인증된 userId가 다른 경우 권한 없음 응답
        if (!userId.equals(requestedUserId)) {
            return ResponseDto.noExistUserId(); // 권한이 없다는 응답
        }

        try {

            // 사용자 존재 여부 확인
            CustomerEntity customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) {
                return ResponseDto.noExistUserId(); // 사용자 ID가 존재하지 않음을 알리는 응답
            }

            customerEntity.setWeight(dto.getWeight());
            customerEntity.setSkeletalMuscleMass(dto.getSkeletalMuscleMass());
            customerEntity.setBodyFatMass(dto.getBodyFatMass());
            customerRepository.save(customerEntity);

            // 새로운 UserMuscleFatEntity 객체 생성
            UserMuscleFatEntity newUserMuscleFat = new UserMuscleFatEntity();
            newUserMuscleFat.setUserId(userId); // 유저 ID 설정
            newUserMuscleFat.setWeight(dto.getWeight());
            newUserMuscleFat.setSkeletalMuscleMass(dto.getSkeletalMuscleMass());
            newUserMuscleFat.setBodyFatMass(dto.getBodyFatMass());
            newUserMuscleFat.setUserMuscleFatDate(LocalDateTime.now()); // 현재 날짜 설정
            // 새로운 기록 저장
            userMuscleFatRepository.save(newUserMuscleFat);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }


    @Override
    public ResponseEntity<ResponseDto> patchThreeMajorLiftCustomer(PatchUserThreeMajorLiftRequestDto dto, String userId, String requestedUserId) {

        // 요청한 userId와 인증된 userId가 다른 경우 권한 없음 응답
        if (!userId.equals(requestedUserId)) {
            return ResponseDto.noExistUserId(); // 권한이 없다는 응답
        }

        try {

            // 사용자 존재 여부 확인
            CustomerEntity customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) {
                return ResponseDto.noExistUserId(); // 사용자 ID가 존재하지 않음을 알리는 응답
            }

            customerEntity.setDeadlift(dto.getDeadlift());
            customerEntity.setBenchPress(dto.getBenchPress());
            customerEntity.setSquat(dto.getSquat());
            customerRepository.save(customerEntity);

            UserThreeMajorLiftEntity newUserThreeMajorLift = new UserThreeMajorLiftEntity();
            newUserThreeMajorLift.setUserId(userId); // 유저 ID 설정
            newUserThreeMajorLift.setDeadlift(dto.getDeadlift());
            newUserThreeMajorLift.setBenchPress(dto.getBenchPress());
            newUserThreeMajorLift.setSquat(dto.getSquat());
            newUserThreeMajorLift.setUserThreeMajorLiftDate(LocalDateTime.now()); // 현재 날짜 설정
            userThreeMajorLiftRepository.save(newUserThreeMajorLift);

                
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

}