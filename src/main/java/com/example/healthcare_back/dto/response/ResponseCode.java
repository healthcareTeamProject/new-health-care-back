package com.example.healthcare_back.dto.response;

public interface ResponseCode {
    
    String SUCCESS = "SU";

    String VALIDATION_FAIL = "VF"; 
    String DUPLICATED_USER_ID = "DI";
    String DUPLICATED_USER_NICKNAME = "DN";  
    String DUPLICATED_USER_TEL_NUMBER = "DT";

    String NO_EXIST_USER_THREE_MAJOR_LIFT_INFORMATION = "NTI";
    String NO_EXIST_USER_ID = "NI";
    String NO_EXIST_COMMENT = "NC";
    String NO_EXIST_BOARD = "NB";

    String TEL_AUTH_FAIL = "TAF";
    String SIGN_IN_FAIL = "SF";
    String AUTHENTICATION_FAIL = "AF";

    String MESSAGE_SEND_FAIL = "TF";
    String TOKEN_CREATE_FAIL = "TCF";
    String DATABASE_ERROR = "DBE";
}
