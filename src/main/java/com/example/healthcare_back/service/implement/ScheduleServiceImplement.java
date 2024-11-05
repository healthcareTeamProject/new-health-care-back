package com.example.healthcare_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.healthcare_back.dto.request.schedule.DeleteHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.DeleteMealScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PatchHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PatchMealScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostMealScheduleRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetMealScheduleResponseDto;
import com.example.healthcare_back.repository.schedule.HealthScheduleRepository;
import com.example.healthcare_back.repository.schedule.MealScheduleDetailRepository;
import com.example.healthcare_back.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImplement implements ScheduleService {

    private final HealthScheduleRepository healthScheduleRepository;
    private final MealScheduleDetailRepository mealScheduleDetailRepository;

    // HealthSchedule

   @Override
    public ResponseEntity<GetHealthScheduleResponseDto> getHealthScheduleById(String userId) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto> postHealthSchedule(String userId, PostHealthScheduleRequestDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto> patchHealthSchedule(String userId, PatchHealthScheduleRequestDto dto) {
                return null;
    }

    @Override
    public ResponseEntity<ResponseDto> deleteHealthSchedule(String userId, DeleteHealthScheduleRequestDto dto) {
        return null;
    }

    // MealSchedule

    @Override
    public ResponseEntity<GetMealScheduleResponseDto> getMealScheduleById(String userId) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto> postMealSchedule(String userId, PostMealScheduleRequestDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto> patchMealSchedule(String userId, PatchMealScheduleRequestDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto> deleteMealSchedule(String userId, DeleteMealScheduleRequestDto dto) {

        try {
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

}
