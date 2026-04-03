package com.studyspot.backend.domain.user.service;

import com.studyspot.backend.domain.user.User;
import com.studyspot.backend.domain.user.UserRepository;
import com.studyspot.backend.domain.user.dto.LoginRequest;
import com.studyspot.backend.domain.user.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    public void signup(SignupRequest request) {
        User user = new User(request.getEmail(), request.getPassword(), request.getNickname());
        userRepository.save(user);
    }

    // 🔑 로그인 추가
    public String login(LoginRequest request) {
        // 1. 이메일로 유저 찾기 (없으면 에러)
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        // 2. 비밀번호 확인 (틀리면 에러)
        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        // 3. 성공하면 임시 토큰(신분증) 발급!
        return "fake-jwt-token-user-" + user.getId();
    }
}