package com.example.healthcare_back.dto.response;

public interface ResponseMessage {

    String SUCCESS = "Success.";

    String VALIDATION_FAIL = "Validation failed."; 
    String DUPLICATED_USER_ID = "Duplicated user id."; 
    String DUPLICATED_USER_NICKNAME = "Duplicated user nickname.";  
    String DUPLICATED_USER_TEL_NUMBER = "Duplicated user tel number.";

    String NO_EXIST_USER_THREE_MAJOR_LIFT_INFORMATION =  "No exist user three major lift information.";
    String NO_EXIST_USER_MUSCLE_FAT_INFORMATION =  "No exist user muscle fat information.";
    String NO_EXIST_USER_ID =  "No exist user id.";
    String NO_EXIST_COMMENT =  "No exist comment.";
    String NO_EXIST_BOARD = "No exist board.";
    String NO_EXIST_SCHEDULE = "No exist schedule.";
    String NO_EXIST_DETAIL = "No exist detail.";

    String TEL_AUTH_FAIL = "Tel number authentication failed.";
    String SIGN_IN_FAIL = "Sign in failed.";
    String AUTHENTICATION_FAIL = "Authentication fail.";

    String MESSAGE_SEND_FAIL = "Auth number send failed.";
    String TOKEN_CREATE_FAIL = "Token creation failed.";
    String DATABASE_ERROR = "Database error.";
}
