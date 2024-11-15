package com.example.healthcare_back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.healthcare_back.dto.request.schedule.PatchHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostHealthScheduleRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleListResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleResponseDto;
import com.example.healthcare_back.entity.customer.CustomerEntity;
import com.example.healthcare_back.entity.schedule.HealthScheduleEntity;
import com.example.healthcare_back.repository.customer.CustomerRepository;
import com.example.healthcare_back.repository.schedule.HealthScheduleRepository;
import com.example.healthcare_back.service.HealthScheduleService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class HealthScheduleServiceImplement implements HealthScheduleService {

    private final HealthScheduleRepository healthScheduleRepository;
    private final CustomerRepository customerRepository;

    // 특정 건강 일정 조회
    @Override
    public ResponseEntity<? super GetHealthScheduleResponseDto> getHealthSchedule(Integer healthScheduleNumber, String userId) {
        HealthScheduleEntity healthScheduleEntity = null;

        try {
            // 특정 healthScheduleNumber와 userId로 건강 일정 조회
            healthScheduleEntity = healthScheduleRepository.findByHealthScheduleNumberAndUserId(healthScheduleNumber, userId);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }
        
        return GetHealthScheduleResponseDto.success(healthScheduleEntity); // 성공적으로 조회 시 응답
    }

    // 사용자 건강 일정 목록 조회
    @Override
    public ResponseEntity<? super GetHealthScheduleListResponseDto> getHealthScheduleList(String userId) {

        List<HealthScheduleEntity> healthScheduleEntities = new ArrayList<>();

        try {
            // 사용자 존재 여부 확인
            CustomerEntity customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) {
                return ResponseDto.noPermission(); // 사용자 ID가 존재하지 않음을 알리는 응답
            }

            // 특정 사용자 ID로 건강 일정 목록 조회
            healthScheduleEntities = healthScheduleRepository.findByUserIdOrderByHealthScheduleNumberDesc(userId);
            if (healthScheduleEntities.isEmpty()) {
                healthScheduleEntities = new ArrayList<>();
            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }

        return GetHealthScheduleListResponseDto.success(healthScheduleEntities); // 성공적으로 조회 시 응답
    }

    // 새로운 건강 일정 생성
    @Override
    public ResponseEntity<ResponseDto> postHealthSchedule(PostHealthScheduleRequestDto dto, String userId) {
        try {
            
            // 사용자 존재 여부 확인
            CustomerEntity customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) {
                return ResponseDto.noExistUserId(); // 사용자 ID가 존재하지 않음을 알리는 응답
            }

            HealthScheduleEntity healthScheduleEntity = new HealthScheduleEntity(dto, userId);
            healthScheduleRepository.save(healthScheduleEntity); // 건강 일정 저장

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }

        return ResponseDto.success(); // 성공 응답
    }

    // 건강 일정 수정
    @Override
    public ResponseEntity<ResponseDto> patchHealthSchedule(PatchHealthScheduleRequestDto dto, Integer healthScheduleNumber, String userId) {
        
        try {

            HealthScheduleEntity hScheduleEntity = healthScheduleRepository.findByHealthScheduleNumberAndUserId(healthScheduleNumber, userId);
            if (hScheduleEntity == null) {
                return ResponseDto.noPermission(); 
            }

            // healthScheduleNumber로 건강 일정 존재 여부 확인
            List<HealthScheduleEntity> healthScheduleEntityList = healthScheduleRepository.findByHealthScheduleNumber(healthScheduleNumber);
            if (healthScheduleEntityList.isEmpty()) {
                healthScheduleEntityList = new ArrayList<>();
            }
            HealthScheduleEntity healthScheduleEntity = healthScheduleEntityList.get(0);

            // 건강 일정 정보 업데이트
            healthScheduleEntity.setHealthTitle(dto.getHealthTitle());
            healthScheduleEntity.setHealthScheduleStart(dto.getHealthScheduleStart());
            healthScheduleEntity.setHealthScheduleEnd(dto.getHealthScheduleEnd());
            healthScheduleRepository.save(healthScheduleEntity); // 변경 사항 저장

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }

        return ResponseDto.success(); // 성공 응답
    }   

    // 건강 일정 삭제
    @Override
    public ResponseEntity<ResponseDto> deleteHealthSchedule(Integer healthScheduleNumber, String userId) {

        // 사용자 존재 여부 확인
        HealthScheduleEntity hScheduleEntity = healthScheduleRepository.findByHealthScheduleNumberAndUserId(healthScheduleNumber, userId);
        if (hScheduleEntity == null) {
            return ResponseDto.noPermission(); 
        }

        try {
            healthScheduleRepository.deleteByHealthScheduleNumber(healthScheduleNumber); // healthScheduleNumber로 일정 삭제

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }   

        return ResponseDto.success(); // 성공 응답
    }

}
