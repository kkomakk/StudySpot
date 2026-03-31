package com.studyspot.backend.domain.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyspot.backend.global.exception.ErrorCode;
import com.studyspot.backend.global.response.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 사용자가 인증 없이 접근했을 때 401 Unauthorized 에러를 처리하는 클래스
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        // 응답 타입을 JSON으로 설정
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // 프로젝트 공통 응답 규격(ApiResponse)에 맞춰 에러 메시지 생성
        ApiResponse<Void> apiResponse = ApiResponse.fail(ErrorCode.UNAUTHORIZED.getMessage());

        // JSON으로 변환하여 응답 바디에 직접 기록
        String result = objectMapper.writeValueAsString(apiResponse);
        response.getWriter().write(result);
    }
}