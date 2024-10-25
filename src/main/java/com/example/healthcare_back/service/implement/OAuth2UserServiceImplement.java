package com.example.healthcare_back.service.implement;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.healthcare_back.common.object.CustomOAuth2User;
import com.example.healthcare_back.entity.CustomerEntity;
import com.example.healthcare_back.provider.JwtProvider;
import com.example.healthcare_back.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service // Spring의 서비스 컴포넌트로 등록
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 생성
public class OAuth2UserServiceImplement extends DefaultOAuth2UserService {
    
    private final JwtProvider jwtProvider; // JWT 생성 및 검증 제공자
    private final CustomerRepository customerRepository; // 고객 정보 조회를 위한 레포지토리

    // OAuth2User를 로드하는 메서드
    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {

        // OAuth2 사용자 정보를 가져옴
        OAuth2User oAuth2User = super.loadUser(request);
        // 클라이언트 등록 정보에서 SNS 제공자 이름을 가져옴
        String registration = request.getClientRegistration().getClientName().toLowerCase();

        // SNS ID를 가져옴
        String snsId = getSnsId(oAuth2User, registration);
        
        // SNS ID와 가입 경로를 사용하여 고객 엔티티 조회
        CustomerEntity customerEntity = customerRepository.findBySnsIdAndJoinPath(snsId, registration);

        CustomOAuth2User customOAuth2User = null;

        // 고객 엔티티가 존재하지 않는 경우 (새로운 사용자)
        if (customerEntity == null) {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("snsId", snsId); // SNS ID 저장
            attributes.put("joinPath", registration); // 가입 경로 저장

            // CustomOAuth2User 객체 생성 (인증되지 않은 사용자)
            customOAuth2User = new CustomOAuth2User(snsId, attributes, false);
        } else {
            // 고객 엔티티가 존재하는 경우 (기존 사용자)
            String userId = customerEntity.getUserId(); // 사용자 ID 가져옴
            String token = jwtProvider.create(userId); // JWT 생성

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("accessToken", token); // 생성한 JWT 저장

            // CustomOAuth2User 객체 생성 (인증된 사용자)
            customOAuth2User = new CustomOAuth2User(userId, attributes, true);
        }
    
        return customOAuth2User; // 사용자 정보를 담고 있는 CustomOAuth2User 반환

    }

    // SNS ID를 가져오는 메서드
    private String getSnsId(OAuth2User oAuth2User, String registration) {
        String snsId = null;

        // SNS 제공자에 따라 ID를 추출
        if (registration.equals("kakao")) {
            snsId = oAuth2User.getName(); // 카카오는 이름을 SNS ID로 사용
        }
        if (registration.equals("naver")) {
            // 네이버는 response 필드에서 ID를 추출
            Map<String, String> response = (Map<String, String>) oAuth2User.getAttributes().get("response");
            snsId = response.get("id");
        }

        return snsId; // 추출한 SNS ID 반환
    }

}