package com.example.healthcare_back.service.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.healthcare_back.dto.request.schedule.PatchHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PatchMealScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostMealScheduleRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetMealScheduleResponseDto;
import com.example.healthcare_back.entity.schedule.HealthScheduleEntity;
import com.example.healthcare_back.repository.schedule.HealthScheduleRepository;
import com.example.healthcare_back.repository.schedule.MealScheduleDetailRepository;
import com.example.healthcare_back.service.ScheduleService;

import lombok.RequiredArgsConstructor;
import okhttp3.Response;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImplement implements ScheduleService {

    private final HealthScheduleRepository healthScheduleRepository;
    private final MealScheduleDetailRepository mealScheduleDetailRepository;

    // HealthSchedule

   @Override
    public ResponseEntity<GetHealthScheduleResponseDto> getHealthScheduleById(Integer healthScheduleNumber) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto> postHealthSchedule(PostHealthScheduleRequestDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto> patchHealthSchedule(Integer healthScheduleNumber,
            PatchHealthScheduleRequestDto dto) {
                return null;
    }

    @Override
    public ResponseEntity<ResponseDto> deleteHealthSchedule(Integer healthScheduleNumber) {
        return null;
    }

    // MealSchedule

    @Override
    public ResponseEntity<GetMealScheduleResponseDto> getMealScheduleById(Integer mealScheduleNumber) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto> postMealSchedule(PostMealScheduleRequestDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto> patchMealSchedule(Integer mealScheduleNumber, PatchMealScheduleRequestDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto> deleteMealSchedule(Integer mealScheduleNumber) {

        try {
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

}
