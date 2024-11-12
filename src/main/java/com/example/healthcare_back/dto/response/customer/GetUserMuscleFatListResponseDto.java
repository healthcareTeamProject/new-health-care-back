package com.example.healthcare_back.dto.response.customer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.common.object.UserMuscleFatList;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.customer.UserMuscleFatEntity;

import lombok.Getter;

// 회원 신체정보 불러오기 Response DTO

@Getter
public class GetUserMuscleFatListResponseDto extends ResponseDto {

    // 사용자 근육/지방 목록을 저장하는 리스트
    private final List<UserMuscleFatList> userMuscleFatLists;

    // 생성자: UserMuscleFatEntity 리스트를 받아 성공 코드와 메시지를 설정하고, UserMuscleFatList로 변환하여 userMuscleFatLists 필드에 할당
    public GetUserMuscleFatListResponseDto(List<UserMuscleFatEntity> userMuscleFatEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userMuscleFatLists = UserMuscleFatList.getList(userMuscleFatEntities);
    }

    // 정적 메서드: 성공적인 요청에 대한 ResponseEntity 객체를 생성하여 반환
    public static ResponseEntity<GetUserMuscleFatListResponseDto> success(List<UserMuscleFatEntity> userMuscleFatEntities) {
        // 응답 본문으로 사용할 GetUserMuscleFatListResponseDto 객체 생성
        GetUserMuscleFatListResponseDto responseBody = new GetUserMuscleFatListResponseDto(userMuscleFatEntities);
        // HTTP 상태 코드를 OK(200)로 설정하여 응답 반환
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}