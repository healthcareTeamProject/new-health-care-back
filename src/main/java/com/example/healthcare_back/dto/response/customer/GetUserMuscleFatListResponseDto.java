package com.example.healthcare_back.dto.response.customer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.common.object.UserMuscleFatList;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.UserMuscleFatEntity;

import lombok.Getter;

// 회원 신체정보 불러오기 Response DTO

@Getter
public class GetUserMuscleFatListResponseDto extends ResponseDto {

    private final List<UserMuscleFatList> userMuscleFatLists;

    public GetUserMuscleFatListResponseDto(List<UserMuscleFatEntity> userMuscleFatEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userMuscleFatLists = UserMuscleFatList.getList(userMuscleFatEntities);
    }

    public static ResponseEntity<GetUserMuscleFatListResponseDto> success(List<UserMuscleFatEntity> userMuscleFatEntities) {
        GetUserMuscleFatListResponseDto responseBody = new GetUserMuscleFatListResponseDto(userMuscleFatEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}