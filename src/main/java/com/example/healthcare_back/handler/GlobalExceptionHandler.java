package com.example.healthcare_back.handler;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.healthcare_back.dto.response.ResponseDto;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 유효성 검사 예외를 처리하는 메서드
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ex.printStackTrace(); // 예외 스택 트레이스를 출력하여 디버깅에 도움을 줌
        // 모든 필드 오류 메시지를 수집하여 하나의 문자열로 반환
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        // 400 (BAD_REQUEST) 상태 코드와 함께 오류 메시지를 반환
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    // JWT 토큰 만료 예외를 처리하는 메서드
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> handleExpiredJwtException(ExpiredJwtException ex) {
        ex.printStackTrace(); // 예외 스택 트레이스를 출력하여 디버깅에 도움을 줌
        // 401 (UNAUTHORIZED) 상태 코드와 함께 토큰 만료 메시지를 반환
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token has expired, please log in again.");
    }

    // 엔티티를 찾을 수 없을 때 발생하는 예외를 처리하는 메서드
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseDto> handleEntityNotFound(EntityNotFoundException ex) {
        ex.printStackTrace(); // 예외 스택 트레이스를 출력하여 디버깅에 도움을 줌
        // 사용자 정의 응답 반환 메서드인 noExistUserId()를 호출하여 응답 반환
        return ResponseDto.noExistUserId();
    }

    // 일반적인 예외를 처리하는 메서드
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleGenericException(Exception ex) {
        ex.printStackTrace(); // 예외 스택 트레이스를 출력하여 디버깅에 도움을 줌
        // 사용자 정의 응답 반환 메서드인 databaseError()를 호출하여 데이터베이스 오류 처리 응답 반환
        return ResponseDto.databaseError();
    }
}
