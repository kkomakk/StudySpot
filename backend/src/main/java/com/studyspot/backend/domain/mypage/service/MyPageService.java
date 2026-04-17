package com.studyspot.backend.domain.mypage.service;

import com.studyspot.backend.domain.mypage.dto.MyPageResponse;
import com.studyspot.backend.domain.mypage.repository.MyPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MyPageRepository myPageRepository;

    public MyPageResponse getMyPageInfo(Long userId) {
        return new MyPageResponse("사용자닉네임", "user@example.com");
    }
}