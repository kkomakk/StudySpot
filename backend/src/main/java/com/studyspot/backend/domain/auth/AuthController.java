package com.studyspot.backend.domain.auth;

import com.studyspot.backend.domain.auth.dto.AuthResponse;
import com.studyspot.backend.domain.auth.dto.LoginRequest;
import com.studyspot.backend.domain.auth.dto.SignupRequest;
import com.studyspot.backend.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ApiResponse.ok("로그인에 성공하였습니다.", response);
    }
}