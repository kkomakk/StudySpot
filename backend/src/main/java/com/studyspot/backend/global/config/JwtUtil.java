package com.studyspot.backend.global.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 🛑 우리 서버만 알고 있는 특수 비밀키입니다! (최소 32글자 이상이어야 안전합니다)
    // 실제 서비스에서는 절대 코드에 직접 쓰지 않고 외부에 숨겨두지만, 지금은 테스트를 위해 여기에 적어둡니다.
    private final String SECRET_KEY = "ThisIsMySuperSecretKeyForStudySpotBoardThisIsMySuperSecretKey";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // ⏱️ 토큰 유효시간 (밀리초 단위, 여기서는 딱 1시간으로 설정!)
    private final long EXPIRATION_TIME = 1000 * 60 * 60;

    // 🟢 1. 진짜 토큰(신분증) 만들기
    public String createToken(String email) {
        return Jwts.builder()
                .setSubject(email) // 토큰 주인이 누구인지 (이메일 기록)
                .setIssuedAt(new Date()) // 언제 발급했는지 기록
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 언제 만료되는지 기록
                .signWith(key, SignatureAlgorithm.HS256) // 우리만의 비밀키로 암호화!
                .compact(); // 꾹꾹 눌러서 하나의 긴 문자열로 완성!
    }

    // 🟡 2. 토큰에서 주인(이메일) 꺼내기 (해독)
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // 암호화할 때 썼던 비밀키로 다시 자물쇠를 엽니다
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // 아까 넣었던 이메일 꺼내기
    }

    // 🔴 3. 토큰이 가짜가 아니고, 시간도 안 지났는지 확인
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true; // 에러 없이 열리면 진짜 토큰!
        } catch (Exception e) {
            return false; // 열다가 에러가 나면 가짜이거나 만료된 토큰!
        }
    }
}