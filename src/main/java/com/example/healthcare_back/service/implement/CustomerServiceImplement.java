package com.example.healthcare_back.service.implement;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.healthcare_back.dto.request.customer.PatchCustomerRequestDto;
import com.example.healthcare_back.dto.request.customer.PatchUserMuscleFatRequestDto;
import com.example.healthcare_back.dto.request.customer.PatchUserThreeMajorLiftRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
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
    public ResponseEntity<? super GetUserMuscleFatListResponseDto> getUserMuscleFatList(String userId) {
        
            // 새로운 예외 처리와 로깅 기능을 추가한 코드
            List<UserMuscleFatEntity> userMuscleFatEntities = new ArrayList<>();

        try {
            // 예외 발생 시 기존 메서드도 포함하여 로깅 가능하게 설정
            userMuscleFatEntities = userMuscleFatRepository.findByUserIdOrderByUserMuscleFatNumberAsc(userId);

            // 사용자 데이터가 없을 경우 예외 처리
            if (userMuscleFatEntities.isEmpty()) {
                return ResponseDto.noExistUserMuscleFatInformation();
            }
        } catch (Exception exception) {
            exception.printStackTrace(); // 로그 출력 추가

            // 예외 발생 시 추가 처리
            return ResponseDto.databaseError();
        }

        return GetUserMuscleFatListResponseDto.success(userMuscleFatEntities);
    }

    @Override
    public ResponseEntity<? super GetUserThreeMajorLiftListResponseDto> getUserThreeMajorLiftList(String userId) {
            List<UserThreeMajorLiftEntity> userThreeMajorLiftEntities = new ArrayList<>();

        try {
            // 추가 로깅과 예외 관리 기능
            userThreeMajorLiftEntities = userThreeMajorLiftRepository.findByUserIdOrderByUserThreeMajorLiftNumberAsc(userId);

            if (userThreeMajorLiftEntities.isEmpty()) {
                return ResponseDto.noExistUserThreeMajorLiftInformation();
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
    public ResponseEntity<ResponseDto> patchUserMuscleFatCustomer(PatchUserMuscleFatRequestDto dto, String userId) {
        
        
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
    public ResponseEntity<ResponseDto> patchThreeMajorLiftCustomer(PatchUserThreeMajorLiftRequestDto dto, String userId) {
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