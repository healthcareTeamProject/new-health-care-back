package com.example.healthcare_back.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.healthcare_back.common.object.MealScheduleList;
import com.example.healthcare_back.common.util.CsvUtil;
import com.example.healthcare_back.dto.request.schedule.PatchHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PatchMealScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostMealScheduleRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleListResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetMealScheduleListResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetMealScheduleResponseDto;
import com.example.healthcare_back.entity.customer.CustomerEntity;
import com.example.healthcare_back.entity.schedule.HealthScheduleEntity;
import com.example.healthcare_back.entity.schedule.MealScheduleDetailEntity;
import com.example.healthcare_back.entity.schedule.MealScheduleEntity;
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

    private final MealScheduleDetailRepository mealScheduleDetailRepository;
    private final HealthScheduleRepository healthScheduleRepository;
    private final MealScheduleRepository mealScheduleRepository;
    private final CustomerRepository customerRepository;
    private final CsvUtil csvUtil;


        @Override
        public ResponseEntity<? super GetHealthScheduleResponseDto> getHealthSchedule(Integer healthScheduleNumber, String userId) {

            HealthScheduleEntity healthScheduleEntity = null;

            try {
                healthScheduleEntity = healthScheduleRepository.findByHealthScheduleNumberAndUserId(healthScheduleNumber, userId);
            
                if (healthScheduleEntity == null) {
                    return ResponseDto.noExistSchedule();
                }
            
            } catch (Exception exception) {
                exception.printStackTrace();
                return ResponseDto.databaseError();
            }
        
            return GetHealthScheduleResponseDto.success(healthScheduleEntity);
        }
        
        

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
                exception.printStackTrace(); 
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

        // ===============================================================================

        @Override
        public ResponseEntity<? super GetMealScheduleResponseDto> getMealSchedule(Integer mealScheduleNumber, String userId) {

            try {
            // MealScheduleEntity 조회 및 존재 여부 확인
            MealScheduleEntity mealSchedule = mealScheduleRepository.findByMealScheduleNumberAndUserId(mealScheduleNumber, userId)
                    .orElse(null);

            if (mealSchedule == null) {
                return ResponseDto.noExistSchedule(); // 스케줄이 없을 경우의 응답
            }

            // 상세 식품 정보 조회 및 DTO 반환
            List<MealScheduleDetailEntity> details = mealScheduleDetailRepository
                    .findByMealSchedule_MealScheduleNumber(mealScheduleNumber);

            return GetMealScheduleResponseDto.success(mealSchedule, details);

        } catch (Exception exception) {
            exception.printStackTrace(); // 예외 로그 출력
            return ResponseDto.databaseError(); // 데이터베이스 오류 응답
        }
    }


        @Override
        public ResponseEntity<? super GetMealScheduleListResponseDto> getMealScheduleList(String userId) {

            List<MealScheduleEntity> mealScheduleEntities = new ArrayList<>();

            try {
                mealScheduleEntities = mealScheduleRepository.findByUserIdOrderByMealScheduleNumberDesc(userId);
        
                if (mealScheduleEntities.isEmpty()) {
                    return ResponseDto.noExistSchedule();
                }
        
                // 각 MealScheduleEntity에 대해 관련 MealScheduleDetailEntity를 조회하고 변환
                List<MealScheduleList> mealScheduleLists = mealScheduleEntities.stream().map(schedule -> {
                    List<MealScheduleDetailEntity> details = mealScheduleDetailRepository
                            .findByMealSchedule_MealScheduleNumber(schedule.getMealScheduleNumber());
                    return new MealScheduleList(schedule, details);
                }).collect(Collectors.toList());
        
                return GetMealScheduleListResponseDto.success(mealScheduleLists);
        
            } catch (Exception exception) {
                exception.printStackTrace();
                return ResponseDto.databaseError();
            }
        }

        @Override
        public ResponseEntity<ResponseDto> postMealSchedule(PostMealScheduleRequestDto dto, String userId) {

            try {
                // 메인 스케줄 생성
                MealScheduleEntity mealScheduleEntity = new MealScheduleEntity(dto, userId);
                mealScheduleRepository.save(mealScheduleEntity);
        
                // mealMemo 리스트를 사용하여 세부 식품 정보 저장
                for (PostMealScheduleRequestDto.MealDetail detail : dto.getMealMemo()) { // mealDetails -> mealMemo
                    MealScheduleDetailEntity detailEntity = new MealScheduleDetailEntity(
                        detail.getMealName(), detail.getMealKcal(), detail.getMealCount(), mealScheduleEntity
                    );
                    mealScheduleDetailRepository.save(detailEntity);
                }
        
            } catch (Exception exception) {
                exception.printStackTrace();
                return ResponseDto.databaseError();
            }
        
            return ResponseDto.success();
        }

        @Override
        public ResponseEntity<ResponseDto> patchMealSchedule(PatchMealScheduleRequestDto dto, Integer mealScheduleNumber) {

            try {
                // 스케줄 여부 확인
                MealScheduleEntity mealScheduleEntity = mealScheduleRepository.findById(mealScheduleNumber)
                        .orElseThrow(() -> new RuntimeException("Schedule not found"));
        
                // 업데이트된 정보를 설정
                mealScheduleEntity.setMealTitle(dto.getMealTitle());
                mealScheduleEntity.setMealScheduleStart(dto.getMealScheduleStart());
                mealScheduleEntity.setMealScheduleEnd(dto.getMealScheduleEnd());
                
                // mealMemo를 List<MealDetail>로 설정한 부분 반영
                mealScheduleRepository.save(mealScheduleEntity);
        
                // 기존 세부 식품 정보 삭제 후 새로 저장
                mealScheduleDetailRepository.deleteByMealSchedule(mealScheduleEntity);
        
                for (PatchMealScheduleRequestDto.MealDetail detail : dto.getMealMemo()) { // dto.getMealMemo() 사용
                    MealScheduleDetailEntity detailEntity = new MealScheduleDetailEntity(
                        detail.getMealName(), detail.getMealKcal(), detail.getMealCount(), mealScheduleEntity
                    );
                    mealScheduleDetailRepository.save(detailEntity);
                }
        
            } catch (RuntimeException exception) {
                exception.printStackTrace();
                return ResponseDto.noExistSchedule();
            } catch (Exception exception) {
                exception.printStackTrace();
                return ResponseDto.databaseError();
            }
        
            return ResponseDto.success();
        }

        @Override
        public ResponseEntity<ResponseDto> deleteMealSchedule(Integer mealScheduleNumber) {

            try {
                // 1. 자식 엔터티들 삭제
                List<MealScheduleDetailEntity> details = mealScheduleDetailRepository.findByMealSchedule_MealScheduleNumber(mealScheduleNumber);
                mealScheduleDetailRepository.deleteAll(details);
        
                // 2. 부모 엔터티 삭제
                mealScheduleRepository.deleteById(mealScheduleNumber);
            } catch (Exception exception) {
                exception.printStackTrace();
                return ResponseDto.databaseError();
            }
        
            return ResponseDto.success();
        }

        @Override
        public ResponseEntity<ResponseDto> deleteMealScheduleDetail(Integer mealScheduleDetailNumber) {
            
            try {
                // MealScheduleDetailEntity가 존재하는지 확인
                Optional<MealScheduleDetailEntity> detailEntityOpt = mealScheduleDetailRepository.findById(mealScheduleDetailNumber);
                
                if (!detailEntityOpt.isPresent()) {
                    return ResponseDto.noExistDetail(); // 식단 상세 정보가 존재하지 않음을 알리는 응답
                }
        
                // 상세 정보 삭제
                mealScheduleDetailRepository.deleteById(mealScheduleDetailNumber);
                return ResponseDto.success(); // 성공 응답
            } catch (Exception exception) {
                exception.printStackTrace();
                return ResponseDto.databaseError(); // 데이터베이스 오류 응답
            }
        }

        /**
        * CSV 파일에서 식단 데이터를 가져와서 저장하는 메서드
        */
        public ResponseEntity<ResponseDto> importMealDataFromCsv() {
            try {
                // CsvUtil을 통해 모든 파일의 데이터를 가져옴
                List<MealScheduleDetailEntity> mealDetails = csvUtil.importMealDataFromCsv();
    
                // MealScheduleDetailEntity 저장
                mealScheduleDetailRepository.saveAll(mealDetails);
    
                return ResponseDto.success();
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseDto.databaseError();
            }
        }
        
    }