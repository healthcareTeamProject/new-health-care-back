package com.example.healthcare_back.dto.response.customer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.common.object.UserThreeMajorLiftList;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.customer.UserThreeMajorLiftEntity;

import lombok.Getter;

// 회원 3대측정 기록 조회 Response DTO

@Getter
public class GetUserThreeMajorLiftListResponseDto extends ResponseDto {

    private List<UserThreeMajorLiftList> userThreeMajorLiftLists;

    // 생성자: UserThreeMajorLiftEntity 리스트를 받아 성공 코드와 메시지를 설정하고,
    // UserThreeMajorLiftList로 변환하여 userThreeMajorLiftLists 필드에 할당
    public GetUserThreeMajorLiftListResponseDto(List<UserThreeMajorLiftEntity> userThreeMajorLiftEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userThreeMajorLiftLists = UserThreeMajorLiftList.getList(userThreeMajorLiftEntities);
    }
    
    // 정적 메서드: 성공적인 요청에 대한 ResponseEntity 객체를 생성하여 반환
    public static ResponseEntity<GetUserThreeMajorLiftListResponseDto> success(List<UserThreeMajorLiftEntity> userThreeMajorLiftEntities) {
        // 응답 본문으로 사용할 GetUserThreeMajorLiftListResponseDto 객체 생성
        GetUserThreeMajorLiftListResponseDto responseBody = new GetUserThreeMajorLiftListResponseDto(userThreeMajorLiftEntities);
        // HTTP 상태 코드를 OK(200)로 설정하여 응답 반환
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
