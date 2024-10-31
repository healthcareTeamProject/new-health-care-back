package com.example.healthcare_back.dto.response.customer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.common.object.UserThreeMajorLiftList;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.UserThreeMajorLiftEntity;

import lombok.Getter;

// 회원 3대측정 기록 조회 Response DTO

@Getter
public class GetUserThreeMajorLiftListResponseDto extends ResponseDto {

    private final List<UserThreeMajorLiftList> userThreeMajorLiftLists;

    public GetUserThreeMajorLiftListResponseDto(List<UserThreeMajorLiftEntity> userThreeMajorLiftEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userThreeMajorLiftLists = UserThreeMajorLiftList.getList(userThreeMajorLiftEntities);
    }

    public static ResponseEntity<GetUserThreeMajorLiftListResponseDto> success(List<UserThreeMajorLiftEntity> userThreeMajorLiftEntity) {
        GetUserThreeMajorLiftListResponseDto responseBody = new GetUserThreeMajorLiftListResponseDto(userThreeMajorLiftEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
