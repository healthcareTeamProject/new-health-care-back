package com.example.healthcare_back.dto.response.customer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.common.object.UserThreeMajorLiftList;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.repository.resultSet.UserThreeMajorLiftResultSet;

import lombok.Getter;

// 회원 3대측정 기록 조회 Response DTO

@Getter
public class GetUserThreeMajorLiftListResponseDto extends ResponseDto {

    private final List<UserThreeMajorLiftList> userThreeMajorLiftLists;

    public GetUserThreeMajorLiftListResponseDto(List<UserThreeMajorLiftResultSet> userThreeMajorLiftResultSet) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userThreeMajorLiftLists = UserThreeMajorLiftList.getList(userThreeMajorLiftResultSet);
    }

    public static ResponseEntity<GetUserThreeMajorLiftListResponseDto> success(List<UserThreeMajorLiftResultSet> userThreeMajorLiftResultSet) {
        GetUserThreeMajorLiftListResponseDto responseBody = new GetUserThreeMajorLiftListResponseDto(userThreeMajorLiftResultSet);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
