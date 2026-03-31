package com.studyspot.backend.global.config;

import com.studyspot.backend.domain.auth.jwt.JwtAccessDeniedHandler;
import com.studyspot.backend.domain.auth.jwt.JwtAuthenticationEntryPoint;
import com.studyspot.backend.domain.auth.jwt.JwtFilter;
import com.studyspot.backend.domain.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 실무형 보안 설정 (JWT 기반)
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // REST API이므로 CSRF 보호 해제
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 안 함
                )
                // 예외 처리 핸들러 (401, 403 에러 처리)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // 로그인, 회원가입 등은 허용
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // 관리자 전용
                        .anyRequest().authenticated() // 그 외는 토큰 필수
                )
                // ID/PW 필터 이전에 JWT 필터가 먼저 검사하도록 순서 배치
                .addFilterBefore(new JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}