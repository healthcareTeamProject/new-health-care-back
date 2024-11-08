package com.example.healthcare_back.service.implement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.healthcare_back.dto.request.schedule.PatchHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostHealthScheduleRequestDto;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.dto.response.customer.GetUserMuscleFatListResponseDto;
import com.example.healthcare_back.dto.response.customer.GetUserThreeMajorLiftListResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleListResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleResponseDto;
import com.example.healthcare_back.entity.customer.CustomerEntity;
import com.example.healthcare_back.entity.customer.UserMuscleFatEntity;
import com.example.healthcare_back.entity.customer.UserThreeMajorLiftEntity;
// import com.example.healthcare_back.dto.response.schedule.MealDetailFoodApiResponseDto;
import com.example.healthcare_back.entity.schedule.HealthScheduleEntity;
import com.example.healthcare_back.repository.customer.CustomerRepository;
import com.example.healthcare_back.repository.schedule.HealthScheduleRepository;
import com.example.healthcare_back.repository.schedule.MealScheduleDetailRepository;
import com.example.healthcare_back.repository.schedule.MealScheduleRepository;
import com.example.healthcare_back.service.ScheduleService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImplement implements ScheduleService {

    private final HealthScheduleRepository healthScheduleRepository;
    private final MealScheduleDetailRepository mealScheduleDetailRepository;
    private final MealScheduleRepository mealScheduleRepository;
    private final CustomerRepository customerRepository;

        @Override
        public ResponseEntity<? super GetHealthScheduleListResponseDto> getHealthScheduleList(String userId) {

            // 새로운 예외 처리와 로깅 기능을 추가한 코드
            List<HealthScheduleEntity> healthScheduleEntities = new ArrayList<>();

            try {
                healthScheduleEntities = healthScheduleRepository.findByUserIdOrderByHealthScheduleNumberDesc(userId);

                if (healthScheduleEntities.isEmpty()) {
                    return ResponseDto.noExistSchedule();
                }

            } catch (Exception exception) {
                exception.printStackTrace(); // 로그 출력 추가

                return ResponseDto.databaseError();
            }

            return GetHealthScheduleListResponseDto.success(healthScheduleEntities);
        }

        @Override
        public ResponseEntity<ResponseDto> postHealthSchedule(PostHealthScheduleRequestDto dto, String userId) {

            try {

                // System.out.println(userId);

                // 사용자 존재 여부 확인
                CustomerEntity customerEntity = customerRepository.findByUserId(userId);
                if (customerEntity == null) {
                    return ResponseDto.noExistUserId(); // 사용자 ID가 존재하지 않음을 알리는 응답
                }

                HealthScheduleEntity healthScheduleEntity = new HealthScheduleEntity(dto, userId);
                healthScheduleRepository.save(healthScheduleEntity);

            } catch (Exception exception) {
                exception.printStackTrace();
                return ResponseDto.databaseError();
            }

            return ResponseDto.success();
        }

        @Override
        public ResponseEntity<ResponseDto> patchHealthSchedule(PatchHealthScheduleRequestDto dto,
                Integer healthScheduleNumber) {

        try {

            // 스케줄 여부 확인
            List<HealthScheduleEntity> healthScheduleEntityList = healthScheduleRepository.findByHealthScheduleNumber(healthScheduleNumber);
            if (healthScheduleEntityList == null || healthScheduleEntityList.isEmpty()) {
                return ResponseDto.noExistSchedule();
            }
            HealthScheduleEntity healthScheduleEntity = healthScheduleEntityList.get(0);

            healthScheduleEntity.setHealthTitle(dto.getHealthTitle());
            healthScheduleEntity.setHealthScheduleStart(dto.getHealthScheduleStart());
            healthScheduleEntity.setHealthScheduleEnd(dto.getHealthScheduleEnd());
            healthScheduleRepository.save(healthScheduleEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }   

        @Override
        public ResponseEntity<ResponseDto> deleteHealthSchedule(Integer healthScheduleNumber) {

            try {
                healthScheduleRepository.deleteByHealthScheduleNumber(healthScheduleNumber);

            } catch (Exception exception) {
                exception.printStackTrace();
                return ResponseDto.databaseError();
            }   

        return ResponseDto.success();
        
        }
}   