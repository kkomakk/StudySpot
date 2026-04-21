package com.studyspot.backend.domain.mypage.service;

import com.studyspot.backend.domain.favorite.repository.FavoriteRepository;
import com.studyspot.backend.domain.mypage.dto.MyPageResponse;
import com.studyspot.backend.domain.review.repository.ReviewRepository;
import com.studyspot.backend.domain.user.User;
import com.studyspot.backend.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final FavoriteRepository favoriteRepository;

    // 1. 이메일로 조회
    @Transactional(readOnly = true)
    public MyPageResponse getMyPageInfoByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return getMyPageInfo(user.getId());
    }

    // 2. ID로 조회
    @Transactional(readOnly = true)
    public MyPageResponse getMyPageInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        long reviewCount = reviewRepository.countByUserId(userId);
        long favoriteCount = favoriteRepository.countByUserId(userId);

        return MyPageResponse.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .bio(user.getBio())
                .reviewCount(reviewCount)
                .favoriteCount(favoriteCount)
                .build();
    }
}