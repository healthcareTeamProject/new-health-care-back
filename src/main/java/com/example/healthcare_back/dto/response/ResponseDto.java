package com.example.healthcare_back.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

    private String code;
    private String message;

    public static ResponseEntity<ResponseDto> success() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> validationFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.VALIDATION_FAIL, ResponseMessage.VALIDATION_FAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicatedUserId() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATED_USER_ID, ResponseMessage.DUPLICATED_USER_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicatedUserNickname() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATED_USER_NICKNAME,
                ResponseMessage.DUPLICATED_USER_NICKNAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicatedUserTelNumber() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATED_USER_TEL_NUMBER,
                ResponseMessage.DUPLICATED_USER_TEL_NUMBER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistUserThreeMajorLiftInformation() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_USER_THREE_MAJOR_LIFT_INFORMATION,
                ResponseMessage.NO_EXIST_USER_THREE_MAJOR_LIFT_INFORMATION);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistUserMuscleFatInformation() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_USER_MUSCLE_FAT_INFORMATION,
                ResponseMessage.NO_EXIST_USER_MUSCLE_FAT_INFORMATION);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistUserId() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_USER_ID, ResponseMessage.NO_EXIST_USER_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NO_EXIST_BOARD, ResponseMessage.NO_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistComment() {
        ResponseDto result = new ResponseDto(ResponseCode.NO_EXIST_COMMENT, ResponseMessage.NO_EXIST_COMMENT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistSchedule() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_SCHEDULE, ResponseMessage.NO_EXIST_SCHEDULE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistDetail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_DETAIL, ResponseMessage.NO_EXIST_DETAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> scheduleLimitExceeded() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SCHEDULE_LIMIT_EXCEEDED, ResponseMessage.SCHEDULE_LIMIT_EXCEEDED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> telAuthFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.TEL_AUTH_FAIL, ResponseMessage.TEL_AUTH_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> signInFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> authenticationFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.AUTHENTICATION_FAIL,
                ResponseMessage.AUTHENTICATION_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> messageSendFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.MESSAGE_SEND_FAIL, ResponseMessage.MESSAGE_SEND_FAIL);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> tokenCreateFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.TOKEN_CREATE_FAIL, ResponseMessage.TOKEN_CREATE_FAIL);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> databaseError() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
    
}
