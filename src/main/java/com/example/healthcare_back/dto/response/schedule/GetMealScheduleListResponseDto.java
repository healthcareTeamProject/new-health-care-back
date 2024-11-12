package com.example.healthcare_back.dto.response.schedule;

import com.example.healthcare_back.common.object.MealScheduleList;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import lombok.Getter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetMealScheduleListResponseDto extends ResponseDto{

    private final List<MealScheduleList> mealScheduleLists;

    public GetMealScheduleListResponseDto(List<MealScheduleList> mealScheduleLists) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.mealScheduleLists = mealScheduleLists;
    }

    public static ResponseEntity<GetMealScheduleListResponseDto> success(List<MealScheduleList> mealScheduleEntities) {
        GetMealScheduleListResponseDto responseBody = new GetMealScheduleListResponseDto(mealScheduleEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
