package com.example.healthcare_back.service.implement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.healthcare_back.dto.request.schedule.MealScheduleDetailRequestDto;
import com.example.healthcare_back.dto.request.schedule.PatchHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PatchMealScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostMealScheduleRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetMealScheduleResponseDto;
// import com.example.healthcare_back.dto.response.schedule.MealDetailFoodApiResponseDto;
import com.example.healthcare_back.entity.schedule.HealthScheduleEntity;
import com.example.healthcare_back.entity.schedule.MealScheduleDetailEntity;
import com.example.healthcare_back.entity.schedule.MealScheduleEntity;
import com.example.healthcare_back.repository.schedule.HealthScheduleRepository;
import com.example.healthcare_back.repository.schedule.MealScheduleDetailRepository;
import com.example.healthcare_back.repository.schedule.MealScheduleRepository;
import com.example.healthcare_back.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImplement implements ScheduleService {

    private final HealthScheduleRepository healthScheduleRepository;
    private final MealScheduleDetailRepository mealScheduleDetailRepository;
    private final MealScheduleRepository mealScheduleRepository;
    private final RestTemplate restTemplate;  // API 호출용 RestTemplate 객체

    //HealthSchedule

    // @Override
    // public ResponseEntity<List<? super GetHealthScheduleResponseDto>> getAllSchedules(String userId) {
    //     try {
            

    //     } catch (Exception exception) {
    //         exception.printStackTrace();
    //         return ResponseDto.databaseError();
            
    //     }

    //     return ResponseDto.success();
    // }

    @Override
    public ResponseEntity<? super GetHealthScheduleResponseDto> getHealthScheduleById(Integer healthScheduleNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHealthScheduleById'");
    }

    @Override
    public ResponseEntity<ResponseDto> postHealthSchedule(PostHealthScheduleRequestDto dto) {

        try {

            HealthScheduleEntity healthScheduleEntity = healthScheduleRepository.findByUserId(dto.getUserId());
            if (healthScheduleEntity == null) {
                return ResponseDto.noExistUserId(); // 사용자 ID가 존재하지 않음을 알리는 응답
            }
            
            HealthScheduleEntity newschedule = new HealthScheduleEntity();
            newschedule.setUserId(dto.getUserId());
            newschedule.setHealthTitle(dto.getHealthTitle());
            newschedule.setHealthMemo(dto.getHealthMemo());
            newschedule.setHealthScheduleStart(dto.getHealthScheduleStart());
            newschedule.setHealthScheduleEnd(dto.getHealthScheduleEnd());
            healthScheduleRepository.save(newschedule);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<ResponseDto> patchHealthSchedule(Integer healthScheduleNumber, PatchHealthScheduleRequestDto dto) {
        try {

            HealthScheduleEntity healthScheduleEntity = healthScheduleRepository.findByHealthScheduleNumber(healthScheduleNumber);
            if (healthScheduleEntity == null) {
                return ResponseDto.noExistSchedule();
            }

            healthScheduleEntity.setHealthTitle(dto.getHealthTitle());
            healthScheduleEntity.setHealthMemo(dto.getHealthMemo());
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

            HealthScheduleEntity healthScheduleEntity = healthScheduleRepository.findByHealthScheduleNumber(healthScheduleNumber);
            if (healthScheduleEntity == null) return ResponseDto.noExistSchedule();

            healthScheduleRepository.delete(healthScheduleEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    //MealScheduleSchedule

    // @Override
    // public ResponseEntity<List<? super GetMealScheduleResponseDto>> getAllMealSchedules(String userId) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getAllMealSchedules'");
    // }

    @Override
    public ResponseEntity<? super GetMealScheduleResponseDto> getMealScheduleById(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMealScheduleById'");
    }

    @Override
    public ResponseEntity<ResponseDto> postMealSchedule(PostMealScheduleRequestDto dto) {

        try {

            MealScheduleEntity mealScheduleEntity = mealScheduleRepository.findByUserId(dto.getUserId());
            if (mealScheduleEntity == null) {
                return ResponseDto.noExistUserId(); // 사용자 ID가 존재하지 않음을 알리는 응답
            }
            
            MealScheduleEntity newschedule = new MealScheduleEntity();
            newschedule.setUserId(dto.getUserId());
            newschedule.setMealTitle(dto.getMealTitle());
            newschedule.setMealMemo(dto.getMealMemo());
            newschedule.setMealScheduleStart(dto.getMealScheduleStart());
            newschedule.setMealScheduleEnd(dto.getMealScheduleEnd());
            mealScheduleRepository.save(newschedule);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<ResponseDto> patchMealSchedule(Integer mealScheduleNumber, PatchMealScheduleRequestDto dto) {

        try {

            MealScheduleEntity mealScheduleEntity = mealScheduleRepository.findByMealScheduleNumber(mealScheduleNumber);
            if (mealScheduleEntity == null) {
                return ResponseDto.noExistSchedule();
            }

            mealScheduleEntity.setMealTitle(dto.getMealTitle());
            mealScheduleEntity.setMealMemo(dto.getMealMemo());
            mealScheduleEntity.setMealScheduleStart(dto.getMealScheduleStart());
            mealScheduleEntity.setMealScheduleEnd(dto.getMealScheduleEnd());
            mealScheduleRepository.save(mealScheduleEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteMealSchedule(Integer mealScheduleNumber) {

        try {

            MealScheduleEntity mealScheduleEntity = mealScheduleRepository.findByMealScheduleNumber(mealScheduleNumber);
            if (mealScheduleEntity == null) return ResponseDto.noExistSchedule();

            mealScheduleRepository.delete(mealScheduleEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

}