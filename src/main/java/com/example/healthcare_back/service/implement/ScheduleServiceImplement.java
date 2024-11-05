package com.example.healthcare_back.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.healthcare_back.dto.request.schedule.PatchHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PatchMealScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostHealthScheduleRequestDto;
import com.example.healthcare_back.dto.request.schedule.PostMealScheduleRequestDto;
import com.example.healthcare_back.dto.response.schedule.GetHealthScheduleResponseDto;
import com.example.healthcare_back.dto.response.schedule.GetMealScheduleResponseDto;
import com.example.healthcare_back.entity.schedule.HealthScheduleEntity;
import com.example.healthcare_back.repository.schedule.HealthScheduleRepository;
import com.example.healthcare_back.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImplement implements ScheduleService {

    private final HealthScheduleRepository healthScheduleRepository;

    // HealthSchedule
    @Override
    public List<GetHealthScheduleResponseDto> getAllHealthSchedules(String userId) {
        List<HealthScheduleEntity> schedules = healthScheduleRepository.findByUserId(userId);
        return schedules.stream()
                .map(schedule -> new GetHealthScheduleResponseDto(schedule))
                .collect(Collectors.toList());
    }

    @Override
    public GetHealthScheduleResponseDto getHealthScheduleById(Integer healthScheduleNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHealthScheduleById'");
    }

    @Override
    public PostHealthScheduleRequestDto postHealthSchedule(PostHealthScheduleRequestDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postHealthSchedule'");
    }

    @Override
    public PatchHealthScheduleRequestDto patchHealthSchedule(Integer healthScheduleNumber,
            PatchHealthScheduleRequestDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patchHealthSchedule'");
    }

    @Override
    public void deleteHealthSchedule(Integer healthScheduleNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteHealthSchedule'");
    }


    // MealSchedule
    @Override
    public List<GetMealScheduleResponseDto> getAllMealSchedules(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllMealSchedules'");
    }

    @Override
    public GetMealScheduleResponseDto getMealScheduleById(Integer mealScheduleNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMealScheduleById'");
    }

    @Override
    public PostMealScheduleRequestDto postMealSchedule(PostMealScheduleRequestDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postMealSchedule'");
    }

    @Override
    public PatchMealScheduleRequestDto patchMealSchedule(Integer mealScheduleNumber, PatchMealScheduleRequestDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patchMealSchedule'");
    }

    @Override
    public void deleteMealSchedule(Integer mealScheduleNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMealSchedule'");
    }

    
}
