package com.example.healthcare_back.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.healthcare_back.filter.JwtAuthenticationFilter;
import com.example.healthcare_back.handler.OAuth2SuccessHandler;
import com.example.healthcare_back.service.implement.OAuth2UserServiceImplement;

import lombok.RequiredArgsConstructor;

// Spring Web 보안 설정 클래스
@Configurable
@Configuration
@EnableWebSecurity // 웹 보안 기능 활성화
@RequiredArgsConstructor // 생성자 주입을 위한 어노테이션
public class WebSecurityConfig {

    // OAuth2 로그인 성공 처리기
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    
    // JWT 인증 필터
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    
    // OAuth2 사용자 서비스 구현체
    private final OAuth2UserServiceImplement oAuth2Service;

    // 보안 필터 체인 설정
    @Bean
    protected SecurityFilterChain configure(HttpSecurity security) throws Exception {

        security
            // 기본 인증 방식 비활성화
            .httpBasic(HttpBasicConfigurer::disable)
            
            // 세션 관리: 상태 없는 세션 사용 (JWT 인증 방식에 적합)
            .sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            
            // CSRF 보호 비활성화 (API 서버에서 주로 비활성화)
            .csrf(CsrfConfigurer::disable)
            
            // CORS 정책 설정 (모든 도메인, 헤더, 메서드 허용)
            .cors(cors -> cors.configurationSource(configurationSource()))
            
            // URL 패턴 및 HTTP 메서드에 따른 인증 및 인가 설정
            .authorizeHttpRequests(request -> request
                // 특정 경로는 모든 사용자에게 허용 (예: 인증 관련 API, 파일 접근)
                .requestMatchers("/api/v1/auth/**", "/oauth2/callback/*", "/file/*", "/").permitAll()

                // 고객 조회 관련 GET 요청은 인증 없이 이용
                .requestMatchers(HttpMethod.GET, "/api/v1/customer/**").permitAll()

                // 게시글 조회 관련 GET 요청은 인증 없이 허용
                .requestMatchers(HttpMethod.GET, "/api/v1/board/**").permitAll()
                
                // 게시글 조회수 증가 관련 PUT 요청도 인증 없이 허용
                .requestMatchers(HttpMethod.PUT, "/api/v1/board/*/view").permitAll()
                
                // 나머지 모든 요청은 인증된 사용자만 접근 가능
                .anyRequest().authenticated()
            )
            
            // 인증 및 인가 중 발생하는 예외 처리
            .exceptionHandling(exception -> exception
                // 인증 실패 시 동작하는 엔트리 포인트 설정
                .authenticationEntryPoint(new AuthenticationFailEntryPoint())
            )
            
            // OAuth2 로그인 설정
            .oauth2Login(oauth2 -> oauth2
                // OAuth2 리디렉션 엔드포인트 설정
                .redirectionEndpoint(endpoint -> endpoint.baseUri("/oauth2/callback/*"))
                
                // OAuth2 인증 엔드포인트 설정
                .authorizationEndpoint(endpoint -> endpoint.baseUri("/api/v1/auth/sns-sign-in"))
                
                // OAuth2 사용자 정보 처리기 설정
                .userInfoEndpoint(endpoint -> endpoint.userService(oAuth2Service))
                
                // OAuth2 로그인 성공 처리기 설정
                .successHandler(oAuth2SuccessHandler)
            )
            
            // JWT 인증 필터 등록 (UsernamePasswordAuthenticationFilter 앞에 위치)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // 보안 필터 체인 빌드 및 반환
        return security.build();
    }

    // CORS 설정 빈
    @Bean
    protected CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // 모든 도메인에서 요청 허용
        configuration.addAllowedOrigin("*");
        
        // 모든 헤더 허용
        configuration.addAllowedHeader("*");
        
        // 모든 HTTP 메서드 허용
        configuration.addAllowedMethod("*");

        // CORS 설정을 특정 경로에 등록
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        // CORS 설정 소스 반환
        return source;
}


}
