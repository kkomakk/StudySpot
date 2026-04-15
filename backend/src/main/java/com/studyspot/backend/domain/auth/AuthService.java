package com.studyspot.backend.domain.auth;

import com.studyspot.backend.domain.auth.dto.*;
import com.studyspot.backend.domain.auth.jwt.JwtProvider;
import com.studyspot.backend.domain.user.User;
import com.studyspot.backend.domain.user.UserRepository;
import com.studyspot.backend.global.exception.CustomException;
import com.studyspot.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private final JavaMailSender mailSender;

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

    /**
     * 비밀번호 찾기 및 인증 메일 발송 로직
     */
    public void sendPasswordResetMail(FindPasswordRequest request) {
        // 1. 이메일과 닉네임으로 유저 존재 여부 확인
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!user.getNickname().equals(request.getNickname())) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        // 2. 재설정용 임시 토큰 생성
        String resetToken = jwtProvider.createToken(user.getEmail(), user.getRole().name());
        String resetLink = "http://localhost:5173/reset-password?token=" + resetToken;

        // 3. 메일 메시지 작성 및 발송
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("[StudySpot] 비밀번호 재설정 안내드립니다.");
            message.setText(user.getNickname() + "님, 안녕하세요.\n\n" +
                    "아래 링크를 클릭하여 비밀번호 재설정을 진행해 주세요.\n" +
                    resetLink + "\n\n" +
                    "만약 본인이 요청한 것이 아니라면 이 메일을 무시하셔도 됩니다.");

            mailSender.send(message);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 비밀번호 재설정 실행 로직
     */
    @Transactional
    public void resetPassword(ResetPasswordRequest request) {
        // 1. 토큰 유효성 검증
        if (!jwtProvider.validateToken(request.getToken())) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        // 2. 토큰에서 이메일 추출
        String email = jwtProvider.getEmail(request.getToken());
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 3. 새 비밀번호 암호화 및 변경
        String encodedPassword = passwordEncoder.encode(request.getNewPassword());
        user.changePassword(encodedPassword);
    }
}