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
import com.example.healthcare_back.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplement implements CustomerService{

    private final CustomerRepository customerRepository;
    private final UserMuscleFatRepository userMuscleFatRepository;
    private final UserMuscleFatEntity userMuscleFatEntity;
    private final UserThreeMajorLiftEntity userThreeMajorLiftEntity;
    

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserMuscleFat'");
    }

    @Override
    public ResponseEntity<? super GetUserThreeMajorLiftResponseDto> getUserThreeMajorLift(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserThreeMajorLift'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserThreeMajorLiftList'");
    }

    @Override
    public ResponseEntity<ResponseDto> signUpUserMuscleFat(@Valid PostUserMuscleFatRequestDto dto) {
        
        Double weight = dto.getWeight();
        Double skeletalMuscleMass = dto.getSkeletalMuscleMass();
        Double bodyFatMass = dto.getBodyFatMass();

        // 유효성 검사: 체중, 골격근량, 체지방량이 음수인지 확인
        if (weight < 0 || skeletalMuscleMass < 0 || bodyFatMass < 0) {
        return ResponseDto.databaseError();
    }

        try {
            // CustomerEntity 객체 생성 및 등록 날짜 설정
            CustomerEntity customerEntity = new CustomerEntity(dto);
            
            // customerEntity에 등록 번호는 JPA가 자동으로 생성
            customerRepository.save(customerEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 성공 시 등록 번호와 등록 날짜를 포함한 응답 생성
        return ResponseDto.success(userMuscleFatEntity.getUserMuscleFatNumber(), userMuscleFatEntity.getUserMuscleFatDate());
    }

    @Override
    public ResponseEntity<ResponseDto> signUpUserThreeMajorLift(@Valid PostUserThreeMajorLiftRequestDto dto) {

        Double deadlift = dto.getDeadlift();
        Double benchPress = dto.getBenchPress();
        Double squat = dto.getSquat();

        try {

            // CustomerEntity 객체 생성 및 등록 날짜 설정
            CustomerEntity customerEntity = new CustomerEntity(dto);
            
            // customerEntity에 등록 번호는 JPA가 자동으로 생성
            customerRepository.save(customerEntity);

            // 유효성 검사: 데드리프트, 벤치프레스, 스쿼트가 음수인지 확인
            if (deadlift < 0 || benchPress < 0 || squat < 0) {
            return ResponseDto.databaseError();
        
        }   catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        // 성공 시 등록 번호와 등록 날짜를 포함한 응답 생성
        return ResponseDto.success(userThreeMajorLiftEntity.getThreeMajorLiftNumber(), userThreeMajorLiftEntity.getThreeMajorLiftDate());
        

        
    }
}
