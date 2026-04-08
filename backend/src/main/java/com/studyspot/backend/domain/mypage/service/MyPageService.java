package com.studyspot.backend.domain.mypage.service;

import com.studyspot.backend.domain.mypage.dto.MyPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyPageService {

    // TODO: 데이터를 긁어모으기 위해 UserRepository, ReviewRepository, FavoriteRepository 등을 주입받으세요.

    @Transactional(readOnly = true)
    public MyPageResponse getMyPageInfo(Long userId) {

        return MyPageResponse.builder()
                .userId(userId)
                .nickname("임시닉네임")
                .build();
    }
}