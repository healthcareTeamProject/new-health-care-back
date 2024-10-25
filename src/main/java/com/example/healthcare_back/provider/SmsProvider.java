package com.example.healthcare_back.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

// Cool SMS 메세지 전송 제공자
@Component // Spring의 컴포넌트로 등록하여 DI(의존성 주입)를 가능하게 함
public class SmsProvider {
    
    private final DefaultMessageService messageService; // 메시지 서비스 인스턴스
    private final String from; // 발신자 번호

    // 생성자: Cool SMS API 키, 비밀 키, 도메인, 발신자 번호를 주입받아 메시지 서비스 초기화
    public SmsProvider(
        @Value("${cool-sms.api-key}") String apiKey, // API 키
        @Value("${cool-sms.secret-key}") String apiSecretKey, // 비밀 키
        @Value("${cool-sms.domain}") String domain, // 도메인
        @Value("${cool-sms.from}") String from // 발신자 번호
    ) {
        // NurigoApp을 통해 Cool SMS 서비스 초기화
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, domain);
        this.from = from; // 발신자 번호 설정
    }

    // SMS 메시지 전송 메서드
    public boolean sendMessage(String to, String authNumber) {
        // 전송할 메시지 객체 생성
        Message message = new Message();
        message.setFrom(from); // 발신자 번호 설정
        message.setTo(to); // 수신자 번호 설정
        // 메시지 내용 설정
        message.setText("HealthCare 인증 번호 [" + authNumber + "] 를 정확히 입력해주세요.");

        // 메시지 전송 요청 객체 생성
        SingleMessageSendingRequest request = new SingleMessageSendingRequest(message);
        // 메시지 전송
        SingleMessageSentResponse response = messageService.sendOne(request);

        // 전송 결과 상태 확인 (상태 코드가 "2000"인 경우 성공)
        boolean resultStatus = response.getStatusCode().equals("2000");
        return resultStatus; // 전송 성공 여부 반환
    }

}