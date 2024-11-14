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

    // 식단 상세 정보 저장소
    private final MealScheduleDetailRepository mealScheduleDetailRepository;
    // 건강 일정 저장소
    private final HealthScheduleRepository healthScheduleRepository;
    // 식단 일정 저장소
    private final MealScheduleRepository mealScheduleRepository;
    // 고객 저장소
    private final CustomerRepository customerRepository;
    // CSV 유틸리티 클래스
    private final CsvUtil csvUtil;

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
                return ResponseDto.noExistUserId(); // 사용자 ID가 존재하지 않음을 알리는 응답
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

            // 사용자 존재 여부 확인
            HealthScheduleEntity hScheduleEntity = healthScheduleRepository.findByHealthScheduleNumberAndUserId(healthScheduleNumber, userId);
            if (hScheduleEntity == null) {
                return ResponseDto.noExistUserId(); // 사용자 ID가 존재하지 않음을 알리는 응답
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
            return ResponseDto.noExistUserId(); // 사용자 ID가 존재하지 않음을 알리는 응답
        }

        try {
            healthScheduleRepository.deleteByHealthScheduleNumber(healthScheduleNumber); // healthScheduleNumber로 일정 삭제

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }   

        return ResponseDto.success(); // 성공 응답
    }

    // ===============================================================================

    // 특정 식단 일정 조회
    @Override
    public ResponseEntity<? super GetMealScheduleResponseDto> getMealSchedule(Integer mealScheduleNumber, String userId) {
        try {
            // MealScheduleEntity 조회 및 존재 여부 확인
            MealScheduleEntity mealSchedule = mealScheduleRepository.findByMealScheduleNumberAndUserId(mealScheduleNumber, userId);
                    
            // 관련된 상세 식품 정보 조회
            List<MealScheduleDetailEntity> details = mealScheduleDetailRepository
                    .findByMealSchedule_MealScheduleNumber(mealScheduleNumber);

            return GetMealScheduleResponseDto.success(mealSchedule, details); // 성공적으로 조회 시 응답

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 응답
        }
    }

    // 사용자 식단 일정 목록 조회
    @Override
    public ResponseEntity<? super GetMealScheduleListResponseDto> getMealScheduleList(String userId) {
        List<MealScheduleEntity> mealScheduleEntities = new ArrayList<>();

        try {
            // 사용자 존재 여부 확인
            CustomerEntity customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) {
                return ResponseDto.noExistUserId(); // 사용자 ID가 존재하지 않음을 알리는 응답
            }


            // 특정 사용자 ID로 식단 일정 목록 조회
            mealScheduleEntities = mealScheduleRepository.findByUserIdOrderByMealScheduleNumberDesc(userId);
        
            if (mealScheduleEntities.isEmpty()) {
                mealScheduleEntities = new ArrayList<>();
            }
        
            // 각 MealScheduleEntity에 대해 관련 MealScheduleDetailEntity를 조회하여 변환
            List<MealScheduleList> mealScheduleLists = mealScheduleEntities.stream().map(schedule -> {
                List<MealScheduleDetailEntity> details = mealScheduleDetailRepository
                        .findByMealSchedule_MealScheduleNumber(schedule.getMealScheduleNumber());
                return new MealScheduleList(schedule, details);
            }).collect(Collectors.toList());
        
            return GetMealScheduleListResponseDto.success(mealScheduleLists); // 성공적으로 조회 시 응답
        
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }
    }

    // 새로운 식단 일정 생성
    @Override
    public ResponseEntity<ResponseDto> postMealSchedule(PostMealScheduleRequestDto dto, String userId) {
        try {

            // 사용자 존재 여부 확인
            CustomerEntity customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) {
                return ResponseDto.noExistUserId(); // 사용자 ID가 존재하지 않음을 알리는 응답
            }

            // 메인 식단 일정 생성
            MealScheduleEntity mealScheduleEntity = new MealScheduleEntity(dto, userId);
            mealScheduleRepository.save(mealScheduleEntity);
        
            // 각 상세 식품 정보 저장
            for (PostMealScheduleRequestDto.MealDetail detail : dto.getMealMemo()) {
                MealScheduleDetailEntity detailEntity = new MealScheduleDetailEntity(
                    detail.getMealName(), detail.getMealKcal(), detail.getMealCount(), mealScheduleEntity
                );
                mealScheduleDetailRepository.save(detailEntity);
            }
        
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }
        
        return ResponseDto.success(); // 성공 응답
    }

    // 식단 일정 수정
    @Override
    public ResponseEntity<ResponseDto> patchMealSchedule(PatchMealScheduleRequestDto dto, Integer mealScheduleNumber, String userId) {
        try {

            // 사용자 존재 여부 확인
            MealScheduleEntity mScheduleEntity = mealScheduleRepository.findByMealScheduleNumberAndUserId(mealScheduleNumber, userId);
            if (mScheduleEntity == null) {
                return ResponseDto.noExistUserId(); // 사용자 ID가 존재하지 않음을 알리는 응답
            }

            // 스케줄 존재 여부 확인
            MealScheduleEntity mealScheduleEntity = mealScheduleRepository.findByMealScheduleNumber(mealScheduleNumber);
            if (mealScheduleEntity == null) {
                return ResponseDto.noExistSchedule();
            }

            // 업데이트된 정보를 설정
            mealScheduleEntity.setMealTitle(dto.getMealTitle());
            mealScheduleEntity.setMealScheduleStart(dto.getMealScheduleStart());
            mealScheduleEntity.setMealScheduleEnd(dto.getMealScheduleEnd());
            
            mealScheduleRepository.save(mealScheduleEntity);
        
            // 기존 세부 식품 정보 삭제 후 새로 저장
            mealScheduleDetailRepository.deleteByMealSchedule(mealScheduleEntity);
        
            for (PatchMealScheduleRequestDto.MealDetail detail : dto.getMealMemo()) {
                MealScheduleDetailEntity detailEntity = new MealScheduleDetailEntity(
                    detail.getMealName(), detail.getMealKcal(), detail.getMealCount(), mealScheduleEntity
                );
                mealScheduleDetailRepository.save(detailEntity);
            }
        
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            return ResponseDto.noExistSchedule(); // 스케줄이 없는 경우 응답
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }
        
        return ResponseDto.success(); // 성공 응답
    }

    // 식단 일정 삭제
    @Override
    public ResponseEntity<ResponseDto> deleteMealSchedule(Integer mealScheduleNumber, String userId) {
        try {
            // 사용자 존재 여부 확인
            MealScheduleEntity mealScheduleEntity = mealScheduleRepository.findByMealScheduleNumberAndUserId(mealScheduleNumber, userId);
            if (mealScheduleEntity == null) {
                return ResponseDto.noExistUserId(); // 사용자 ID가 존재하지 않음을 알리는 응답
            }

            // 자식 엔티티들(세부 식품 정보) 삭제
            List<MealScheduleDetailEntity> details = mealScheduleDetailRepository.findByMealSchedule_MealScheduleNumber(mealScheduleNumber);
            mealScheduleDetailRepository.deleteAll(details);
        
            // 부모 엔티티(식단 일정) 삭제
            mealScheduleRepository.deleteById(mealScheduleNumber);
        
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }
        
        return ResponseDto.success(); // 성공 응답
    }

    // 특정 식단 상세 일정 삭제
    @Override
    public ResponseEntity<ResponseDto> deleteMealScheduleDetail(Integer mealScheduleDetailNumber, String userId) {

        try {
            // mealScheduleDetailNumber와 userId로 MealScheduleDetailEntity 찾기
            Optional<MealScheduleDetailEntity> detailEntityOpt = mealScheduleDetailRepository
                    .findByMealScheduleDetailNumberAndMealSchedule_UserId(mealScheduleDetailNumber, userId);
    
            if (!detailEntityOpt.isPresent()) {
                return ResponseDto.noPermission();
            }
    
            // 상세 정보 삭제
            mealScheduleDetailRepository.deleteById(mealScheduleDetailNumber);
            return ResponseDto.success(); // 성공 응답
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }
    }

    /**
     * CSV 파일에서 식단 데이터를 가져와서 저장하는 메서드
     */
    public ResponseEntity<ResponseDto> importMealDataFromCsv() {
        try {
            // CsvUtil을 통해 CSV 파일의 데이터를 가져옴
            List<MealScheduleDetailEntity> mealDetails = csvUtil.importMealDataFromCsv();
    
            // MealScheduleDetailEntity 저장
            mealScheduleDetailRepository.saveAll(mealDetails);
    
            return ResponseDto.success(); // 성공 응답
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError(); // 데이터베이스 오류 시 응답
        }
    }
}
