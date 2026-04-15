package com.studyspot.backend.domain.auth;

import com.studyspot.backend.domain.auth.dto.*;
import com.studyspot.backend.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // FR-12: 회원가입 API
    @PostMapping("/signup")
    public ApiResponse<Void> signup(@Valid @RequestBody SignupRequest request) {
        authService.signup(request);
        return ApiResponse.ok("회원가입에 성공하였습니다.");
    }

    // FR-12: 로그인 API
    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ApiResponse.ok("로그인에 성공하였습니다.", response);
    }

    /**
     * 비밀번호 찾기 API (이메일 & 닉네임 검증 후 메일 발송)
     */
    @PostMapping("/find-password")
    public ApiResponse<Void> findPassword(@RequestBody FindPasswordRequest request) {
        authService.sendPasswordResetMail(request);
        return ApiResponse.ok("인증 메일이 성공적으로 발송되었습니다.");
    }

    /**
     * 비밀번호 재설정 API (토큰 검증 후 새 비밀번호 저장)
     */
    @PostMapping("/reset-password")
    public ApiResponse<Void> resetPassword(@RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request);
        return ApiResponse.ok("비밀번호가 성공적으로 변경되었습니다.");
    }
}