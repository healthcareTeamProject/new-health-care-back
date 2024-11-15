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

    private final List<MealScheduleList> mealSchedulelist;

    // 생성자: MealScheduleList 리스트를 받아 성공 코드와 메시지를 설정하고,
    // mealSchedulelist 필드에 할당
    public GetMealScheduleListResponseDto(List<MealScheduleList> mealSchedulelist) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.mealSchedulelist = mealSchedulelist;
    }
    
    // 정적 메서드: 성공적인 요청에 대한 ResponseEntity 객체를 생성하여 반환
    public static ResponseEntity<GetMealScheduleListResponseDto> success(List<MealScheduleList> mealScheduleEntities) {
        // 응답 본문으로 사용할 GetMealScheduleListResponseDto 객체 생성
        GetMealScheduleListResponseDto responseBody = new GetMealScheduleListResponseDto(mealScheduleEntities);
        // HTTP 상태 코드를 OK(200)로 설정하여 응답 반환
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
