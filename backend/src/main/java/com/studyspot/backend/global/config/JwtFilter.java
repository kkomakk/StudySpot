package com.studyspot.backend.global.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

// 🛑 경비원이 요청을 받을 때마다 한 번씩(OncePerRequest) 거치게 되는 스캐너입니다.
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. 방문자의 요청(Header)에서 "Authorization" 이라는 이름의 신분증을 꺼냅니다.
        String header = request.getHeader("Authorization");

        // 2. 신분증 내용이 우리가 아까 발급한 '가짜 토큰'과 똑같은지 확인합니다.
        if (header != null && header.equals("Bearer fake-jwt-token-user-1")) {

            // 3. 맞다면! 경비원의 명부에 "이 사람 신분 확인됐음(user1)!" 이라고 도장을 찍어줍니다.
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken("user1", null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 4. 스캐너 검사가 끝났으니, 다음 단계로 통과시킵니다.
        filterChain.doFilter(request, response);
    }
}