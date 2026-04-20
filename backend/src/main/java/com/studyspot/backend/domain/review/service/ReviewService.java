package com.studyspot.backend.domain.review.service;

import com.studyspot.backend.domain.place.Place;
import com.studyspot.backend.domain.place.PlaceRepository;
import com.studyspot.backend.domain.review.dto.ReviewRequestDto;
import com.studyspot.backend.domain.review.entity.Review;
import com.studyspot.backend.domain.review.repository.ReviewRepository;
import com.studyspot.backend.domain.user.User;
import com.studyspot.backend.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    // 1. 리뷰 저장하기
    @Transactional
    public Review createReview(ReviewRequestDto dto) {
        // 유저 조회
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다. ID: " + dto.getUserId()));

        Place place = placeRepository.findByExternalId(String.valueOf(dto.getPlaceId()))
                .orElseGet(() -> {
                    Place newPlace = Place.builder()
                            .externalId(String.valueOf(dto.getPlaceId())) // 카카오맵 고유 ID 저장
                            .name(dto.getPlaceName() != null ? dto.getPlaceName() : "이름 없음")
                            .address(dto.getPlaceAddress() != null ? dto.getPlaceAddress() : "주소 없음")
                            .build();
                    return placeRepository.save(newPlace);
                });

        // 리뷰 엔티티 생성
        Review review = Review.builder()
                .user(user)
                .place(place)
                .content(dto.getContent())
                .rating(dto.getRating())
                .build();

        // 리뷰 저장
        Review savedReview = reviewRepository.save(review);

        // 장소 평점/리뷰수 업데이트
        place.updateStatistics(dto.getRating());

        return savedReview;
    }

    @Transactional(readOnly = true)
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // 2. 마이페이지용 리뷰 총 개수 조회
    @Transactional(readOnly = true)
    public long getUserReviewCount(Long userId) {
        return reviewRepository.countByUserId(userId);
    }

    // 3. 마이페이지용 리뷰 목록 최신순 조회
    @Transactional(readOnly = true)
    public List<Review> getUserReviews(Long userId) {
        return reviewRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    // 4. 장소 상세페이지용 리뷰 목록 최신순 조회 (카카오맵 ID 기준)
    @Transactional(readOnly = true)
    public List<Review> getPlaceReviews(String externalId) {
        return reviewRepository.findByPlace_ExternalIdOrderByCreatedAtDesc(externalId);
    }
}