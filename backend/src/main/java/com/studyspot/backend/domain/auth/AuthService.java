package com.studyspot.backend.domain.auth;

import com.studyspot.backend.domain.auth.dto.AuthResponse;
import com.studyspot.backend.domain.auth.dto.LoginRequest;
import com.studyspot.backend.domain.auth.dto.SignupRequest;
import com.studyspot.backend.domain.auth.jwt.JwtProvider;
import com.studyspot.backend.domain.user.User;
import com.studyspot.backend.domain.user.UserRepository;
import com.studyspot.backend.global.exception.CustomException;
import com.studyspot.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    /**
     * 회원가입 로직 (FR-12)
     */
    @Transactional
    public void signup(SignupRequest request) {
        // 1. 이메일 중복 체크
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        // 2. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 3. DTO를 엔티티로 변환하여 저장
        User user = User.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .nickname(request.getNickname())
                .build();

        userRepository.save(user);
    }

    /**
     * 로그인 로직 (FR-12)
     */
    public AuthResponse login(LoginRequest request) {
        // 1. 유저 확인
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 2. 비밀번호 일치 확인
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        // 3. JWT 토큰 생성 및 응답 객체 반환
        String token = jwtProvider.createToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token, user.getNickname(), user.getRole().name());
    }
}