package com.example.healthcare_back.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.filter.JwtAuthenticationFilter;
import com.example.healthcare_back.handler.OAuth2SuccessHandler;
import com.example.healthcare_back.service.implement.OAuth2UserServiceImplement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

// Spring Web 보안 설정 클래스
@Configurable
@Configuration
@EnableWebSecurity // 웹 보안 기능 활성화
@RequiredArgsConstructor // 생성자 주입을 위한 어노테이션
public class WebSecurityConfig {

    private final OAuth2SuccessHandler oAuth2SuccessHandler; // OAuth2 로그인 성공 처리기
    private final JwtAuthenticationFilter jwtAuthenticationFilter; // JWT 인증 필터
    private final OAuth2UserServiceImplement oAuth2Service; // OAuth2 사용자 서비스 구현체
    
    @Bean
    protected SecurityFilterChain configure(HttpSecurity security) throws Exception {

        security
            // 기본 인증 방식 비활성화
            .httpBasic(HttpBasicConfigurer::disable)
            // 세션 관리: 상태 없는 세션 사용
            .sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            // CSRF 보호 비활성화
            .csrf(CsrfConfigurer::disable)
            // CORS 정책 설정
            .cors(cors -> cors.configurationSource(configurationSource()))
            // URL 패턴 및 HTTP 메서드에 따른 인증 및 인가 설정
            .authorizeHttpRequests(request -> request
                .requestMatchers("/api/v1/auth/**", "/oauth2/callback/*", "/file/*", "/").permitAll() // 특정 경로는 모든 사용자에게 허용
                .anyRequest().authenticated() // 나머지 요청은 인증된 사용자만 접근 가능
            )
            // 인증 및 인가 중 발생하는 예외 처리
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(new AuthenticationFailEntryPoint()) // 인증 실패 처리기 등록
            )
            // OAuth2 로그인 설정
            .oauth2Login(oauth2 -> oauth2
                .redirectionEndpoint(endpoint -> endpoint.baseUri("/oauth2/callback/*")) // 리디렉션 엔드포인트 설정
                .authorizationEndpoint(endpoint -> endpoint.baseUri("/api/v1/auth/sns-sign-in")) // 인증 엔드포인트 설정
                .userInfoEndpoint(endpoint -> endpoint.userService(oAuth2Service)) // 사용자 정보 처리기 설정
                .successHandler(oAuth2SuccessHandler) // 성공 처리기 설정
            )   
            // JWT 인증 필터 등록
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return security.build(); // 보안 필터 체인 빌드 및 반환
    }

    @Bean
        protected CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
}

}
