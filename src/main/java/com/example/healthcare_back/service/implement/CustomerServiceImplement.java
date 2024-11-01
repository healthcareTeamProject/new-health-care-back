package com.example.healthcare_back.service.implement;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.healthcare_back.dto.request.customer.PatchCustomerRequestDto;
import com.example.healthcare_back.dto.request.customer.PatchUserMuscleFatRequestDto;
import com.example.healthcare_back.dto.request.customer.PatchUserThreeMajorLiftRequestDto;
import com.example.healthcare_back.dto.request.customer.PostUserMuscleFatRequestDto;
import com.example.healthcare_back.dto.request.customer.PostUserThreeMajorLiftRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.customer.GetCustomerResponseDto;
import com.example.healthcare_back.dto.response.customer.GetSignInResponseDto;
import com.example.healthcare_back.dto.response.customer.GetUserMuscleFatListResponseDto;
import com.example.healthcare_back.dto.response.customer.GetUserThreeMajorLiftListResponseDto;
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
            userMuscleFatEntities = userMuscleFatRepository.findByUserIdOrderByUserMuscleFatNumberDesc(userId);

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
            userThreeMajorLiftEntities = userThreeMajorLiftRepository.findByUserIdOrderByUserThreeMajorLiftNumberDesc(userId);

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
    public ResponseEntity<ResponseDto> postUserMuscleFat(PostUserMuscleFatRequestDto dto, String userId) {
        try {

            // 사용자 존재 여부 확인
            CustomerEntity customerEntity = customerRepository.findByUserId(dto.getUserId());
            if (customerEntity == null) {
                return ResponseDto.noExistUserId(); // 사용자 ID가 존재하지 않음을 알리는 응답
            }

            // 새로운 UserMuscleFatEntity 객체 생성
            UserMuscleFatEntity newRecord = new UserMuscleFatEntity();
            newRecord.setUserId(userId); // 유저 ID 설정
            newRecord.setWeight(dto.getWeight()); // 체중 설정
            newRecord.setSkeletalMuscleMass(dto.getSkeletalMuscleMass()); // 골격근량 설정
            newRecord.setBodyFatMass(dto.getBodyFatMass()); // 체지방량 설정
            newRecord.setUserMuscleFatDate(LocalDateTime.now()); // 현재 날짜 설정

            // 새로운 기록 저장
            userMuscleFatRepository.save(newRecord);

        return ResponseDto.successWithUserId(dto.getUserId()); // 성공적으로 저장된 경우 응답


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 오류 발생 시 데이터베이스 오류 응답
        }

    }

    @Override
    public ResponseEntity<ResponseDto> postUserThreeMajorLift(PostUserThreeMajorLiftRequestDto dto, String userId) {
        try {

            // 사용자 존재 여부 확인
            CustomerEntity customerEntity = customerRepository.findByUserId(dto.getUserId());
            if (customerEntity == null) {
                return ResponseDto.noExistUserId(); // 사용자 ID가 존재하지 않음을 알리는 응답
            }

            // 새로운 UserThreeMajorLiftEntity 객체 생성
            UserThreeMajorLiftEntity newRecord = new UserThreeMajorLiftEntity();
            newRecord.setUserId(userId); // 유저 ID 설정
            newRecord.setDeadlift(dto.getDeadlift()); // 데드리프트 설정
            newRecord.setBenchPress(dto.getBenchPress()); // 벤치프레스 설정
            newRecord.setSquat(dto.getSquat()); // 스쿼트 설정
            newRecord.setUserThreeMajorLiftDate(LocalDateTime.now()); // 현재 날짜 설정

            // 새로운 기록 저장
            userThreeMajorLiftRepository.save(newRecord);

        return ResponseDto.successWithUserId(dto.getUserId()); // 성공적으로 저장된 경우 응답

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 오류 발생 시 데이터베이스 오류 응답
        }

    }

    @Override
    public ResponseEntity<ResponseDto> patchCustomer(PatchCustomerRequestDto dto, String userId) {
        
        try {

            String name = dto.getName();
            String nickname = dto.getNickname();
            String profileImage = dto.getProfileImage();
            String personalGoals = dto.getPersonalGoals();
            BigDecimal height = dto.getHeight();

            CustomerEntity customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) return ResponseDto.noExistUserId();
            customerEntity.setName(name);
            customerEntity.setNickname(nickname);
            customerEntity.setProfileImage(profileImage);
            customerEntity.setPersonalGoals(personalGoals);
            customerEntity.setHeight(height);
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

            CustomerEntity customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) return ResponseDto.noExistUserId();
            customerEntity.setWeight(dto.getWeight());
            customerEntity.setSkeletalMuscleMass(dto.getSkeletalMuscleMass());
            customerEntity.setBodyFatMass(dto.getBodyFatMass());
            customerRepository.save(customerEntity);
                
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }


    @Override
    public ResponseEntity<ResponseDto> patchThreeMajorLiftCustomer(PatchUserThreeMajorLiftRequestDto dto, String userId) {
        try {

            CustomerEntity customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) return ResponseDto.noExistUserId();
            customerEntity.setDeadlift(dto.getDeadlift());
            customerEntity.setBenchPress(dto.getBenchPress());
            customerEntity.setSquat(dto.getSquat());
            customerRepository.save(customerEntity);
                
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    

   


}