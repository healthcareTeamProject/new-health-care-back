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

@Getter
public class GetUserThreeMajorLiftListResponseDto extends ResponseDto {

    private List<UserThreeMajorLiftList> userThreeMajorLiftLists;

    public GetUserThreeMajorLiftListResponseDto(List<UserThreeMajorLiftEntity> userThreeMajorLiftEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userThreeMajorLiftLists = UserThreeMajorLiftList.getList(userThreeMajorLiftEntities);
    }

    public static ResponseEntity<GetUserThreeMajorLiftListResponseDto> success(List<UserThreeMajorLiftEntity> userThreeMajorLiftEntities) {
        GetUserThreeMajorLiftListResponseDto responseBody = new GetUserThreeMajorLiftListResponseDto(userThreeMajorLiftEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
