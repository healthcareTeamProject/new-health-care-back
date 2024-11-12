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

    // 고객 저장소
    private final CustomerRepository customerRepository;
    // 사용자 근육/지방 데이터 저장소
    private final UserMuscleFatRepository userMuscleFatRepository;
    // 사용자 3대 운동 데이터 저장소
    private final UserThreeMajorLiftRepository userThreeMajorLiftRepository;

    // 사용자 로그인 정보 조회
    @Override
    public ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId) {
        
        CustomerEntity customerEntity;

        try {
            // 주어진 userId로 고객 조회
            customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) return ResponseDto.noExistUserId(); // 고객이 없을 경우

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }

        return GetSignInResponseDto.success(customerEntity); // 성공적으로 조회 시 응답
    }

    // 고객 정보 조회
    @Override
    public ResponseEntity<? super GetCustomerResponseDto> getCustomer(String userId) {
       
        CustomerEntity customerEntity;

        try {
            // 주어진 userId로 고객 조회
            customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) return ResponseDto.noExistUserId(); // 고객이 없을 경우

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }
        
        return GetCustomerResponseDto.success(customerEntity); // 성공적으로 조회 시 응답
    }

    // 사용자 근육/지방 데이터 리스트 조회
    @Override
    public ResponseEntity<? super GetUserMuscleFatListResponseDto> getUserMuscleFatList(String userId) {
        
        List<UserMuscleFatEntity> userMuscleFatEntities = new ArrayList<>();

        try {
            // 주어진 userId로 사용자 근육/지방 데이터 조회
            userMuscleFatEntities = userMuscleFatRepository.findByUserIdOrderByUserMuscleFatNumberAsc(userId);
            
            // 데이터가 없을 경우
            if (userMuscleFatEntities.isEmpty()) {
                return ResponseDto.noExistUserMuscleFatInformation(); // 사용자 근육/지방 정보 없음 응답
            }

        } catch (Exception exception) {
            exception.printStackTrace(); // 오류 로그 출력
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }

        return GetUserMuscleFatListResponseDto.success(userMuscleFatEntities); // 성공적으로 조회 시 응답
    }

    // 사용자 3대 운동 기록 리스트 조회
    @Override
    public ResponseEntity<? super GetUserThreeMajorLiftListResponseDto> getUserThreeMajorLiftList(String userId) {
        
        List<UserThreeMajorLiftEntity> userThreeMajorLiftEntities = new ArrayList<>();

        try {
            // 주어진 userId로 사용자 3대 운동 데이터 조회
            userThreeMajorLiftEntities = userThreeMajorLiftRepository.findByUserIdOrderByUserThreeMajorLiftNumberAsc(userId);

            // 데이터가 없을 경우
            if (userThreeMajorLiftEntities.isEmpty()) {
                return ResponseDto.noExistUserThreeMajorLiftInformation(); // 사용자 3대 운동 정보 없음 응답
            }

        } catch (Exception exception) {
            exception.printStackTrace(); // 오류 로그 출력
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }

        return GetUserThreeMajorLiftListResponseDto.success(userThreeMajorLiftEntities); // 성공적으로 조회 시 응답
    }

    // 고객 정보 수정
    @Override
    public ResponseEntity<ResponseDto> patchCustomer(PatchCustomerRequestDto dto, String userId) {
        
        try {
            // 주어진 userId로 고객 조회
            CustomerEntity customerEntity = customerRepository.findByUserId(userId);

            if (customerEntity == null) {
                return ResponseDto.noExistUserId(); // 고객이 없을 경우 응답
            }

            // 고객 정보 업데이트
            customerEntity.setName(dto.getName());
            customerEntity.setNickname(dto.getNickname());
            customerEntity.setProfileImage(dto.getProfileImage());
            customerEntity.setPersonalGoals(dto.getPersonalGoals());
            customerEntity.setHeight(dto.getHeight());
            customerRepository.save(customerEntity); // 변경 사항 저장
                
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }

        return ResponseDto.success(); // 성공 응답
    }

    // 사용자 근육/지방 정보 업데이트
    @Override
    public ResponseEntity<ResponseDto> patchUserMuscleFatCustomer(PatchUserMuscleFatRequestDto dto, String userId) {
        
        try {
            // 주어진 userId로 고객 조회
            CustomerEntity customerEntity = customerRepository.findByUserId(userId);
          
            if (customerEntity == null) {
                return ResponseDto.noExistUserId(); // 고객이 없을 경우 응답
            }

            // 고객의 근육/지방 정보 업데이트
            customerEntity.setWeight(dto.getWeight());
            customerEntity.setSkeletalMuscleMass(dto.getSkeletalMuscleMass());
            customerEntity.setBodyFatMass(dto.getBodyFatMass());
            customerRepository.save(customerEntity); // 변경 사항 저장

            // 새로운 UserMuscleFatEntity 객체 생성 후 저장
            UserMuscleFatEntity newUserMuscleFat = new UserMuscleFatEntity();
            newUserMuscleFat.setUserId(userId); // 유저 ID 설정
            newUserMuscleFat.setWeight(dto.getWeight());
            newUserMuscleFat.setSkeletalMuscleMass(dto.getSkeletalMuscleMass());
            newUserMuscleFat.setBodyFatMass(dto.getBodyFatMass());
            newUserMuscleFat.setUserMuscleFatDate(LocalDateTime.now()); // 현재 날짜 설정
            userMuscleFatRepository.save(newUserMuscleFat);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }

        return ResponseDto.success(); // 성공 응답
    }

    // 사용자 3대 운동 정보 업데이트
    @Override
    public ResponseEntity<ResponseDto> patchThreeMajorLiftCustomer(PatchUserThreeMajorLiftRequestDto dto, String userId) {
        
        try {
            // 주어진 userId로 고객 조회
            CustomerEntity customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) {
                return ResponseDto.noExistUserId(); // 고객이 없을 경우 응답
            }

            // 고객의 3대 운동 정보 업데이트
            customerEntity.setDeadlift(dto.getDeadlift());
            customerEntity.setBenchPress(dto.getBenchPress());
            customerEntity.setSquat(dto.getSquat());
            customerRepository.save(customerEntity); // 변경 사항 저장

            // 새로운 UserThreeMajorLiftEntity 객체 생성 후 저장
            UserThreeMajorLiftEntity newUserThreeMajorLift = new UserThreeMajorLiftEntity();
            newUserThreeMajorLift.setUserId(userId); // 유저 ID 설정
            newUserThreeMajorLift.setDeadlift(dto.getDeadlift());
            newUserThreeMajorLift.setBenchPress(dto.getBenchPress());
            newUserThreeMajorLift.setSquat(dto.getSquat());
            newUserThreeMajorLift.setUserThreeMajorLiftDate(LocalDateTime.now()); // 현재 날짜 설정
            userThreeMajorLiftRepository.save(newUserThreeMajorLift);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }

        return ResponseDto.success(); // 성공 응답
    }
}
