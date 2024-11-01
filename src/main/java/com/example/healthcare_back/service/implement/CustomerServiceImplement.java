package com.example.healthcare_back.service.implement;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.healthcare_back.dto.request.auth.SignUpUserMuscleFatRequestDto;
import com.example.healthcare_back.dto.request.auth.SignUpUserThreeMajorLiftRequestDto;
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

        List<UserMuscleFatEntity> userMuscleFatEntities = new ArrayList<>();

        try {

            userMuscleFatEntities = userMuscleFatRepository.findByOrderByUserMuscleFatNumberDesc();

            if (userMuscleFatEntities.isEmpty()) {
                return ResponseDto.noExistUserMuscleFatInformation(); // 예외적인 응답 처리
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserMuscleFatListResponseDto.success(userMuscleFatEntities);
    }

    @Override
    public ResponseEntity<? super GetUserThreeMajorLiftListResponseDto> getUserThreeMajorLiftList(String userId) {
        
        List<UserThreeMajorLiftEntity> userThreeMajorLiftEntities = new ArrayList<>();

        try {

            userThreeMajorLiftEntities = userThreeMajorLiftRepository.findByOrderByUserThreeMajorLiftNumberDesc();

            if (userThreeMajorLiftEntities.isEmpty()) {
                return ResponseDto.noExistUserThreeMajorLiftInformation(); // 예외적인 응답 처리
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserThreeMajorLiftListResponseDto.success(userThreeMajorLiftEntities);
    }

   @Override
    public ResponseEntity<ResponseDto> signUpUserMuscleFat(SignUpUserMuscleFatRequestDto dto, String userId) {
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
    public ResponseEntity<ResponseDto> signUpUserThreeMajorLift(SignUpUserThreeMajorLiftRequestDto dto, String userId) {
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

             // 새로운 기록 추가
            UserMuscleFatEntity newRecord = new UserMuscleFatEntity();
            newRecord.setUserId(userId); // 유저 ID
            newRecord.setWeight(dto.getWeight());
            newRecord.setSkeletalMuscleMass(dto.getSkeletalMuscleMass());
            newRecord.setBodyFatMass(dto.getBodyFatMass());
            newRecord.setUserMuscleFatDate(LocalDateTime.now()); // 현재 날짜 사용
            userMuscleFatRepository.save(newRecord); // 새로운 기록 저장

                
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchThreeMajorLiftCustomer(PatchUserThreeMajorLiftRequestDto dto, String userId) {

        try {

            BigDecimal deadlift = dto.getDeadlift();
            BigDecimal benchPress = dto.getBenchPress();
            BigDecimal squat = dto.getSquat();

            CustomerEntity customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) return ResponseDto.noExistUserId();
            customerEntity.setDeadlift(deadlift);
            customerEntity.setBenchPress(benchPress);
            customerEntity.setSquat(squat);
            customerRepository.save(customerEntity);


            // 새로운 기록 추가
            UserThreeMajorLiftEntity newRecord = new UserThreeMajorLiftEntity();
            newRecord.setUserId(userId); // 유저 ID
            newRecord.setDeadlift(dto.getDeadlift());
            newRecord.setBenchPress(dto.getBenchPress());
            newRecord.setSquat(dto.getSquat());
            newRecord.setUserThreeMajorLiftDate(LocalDateTime.now()); // 현재 날짜 사용
            userThreeMajorLiftRepository.save(newRecord); // 새로운 기록 저장
                
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }


}