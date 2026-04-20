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

    @Transactional(readOnly = true)
    public MyPageResponse getMyPageInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. ID: " + userId));

        long reviewCount = reviewRepository.countByUserId(userId);
        long favoriteCount = favoriteRepository.countByUserId(userId);

        return MyPageResponse.builder()
                .nickname(user.getNickname())
                .email(user.getEmail())
                .bio(user.getBio())
                .reviewCount(reviewCount)
                .favoriteCount(favoriteCount)
                .build();
    }
}