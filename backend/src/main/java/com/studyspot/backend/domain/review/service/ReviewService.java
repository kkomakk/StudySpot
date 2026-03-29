package com.studyspot.backend.domain.review.service;

import com.studyspot.backend.domain.review.dto.ReviewRequestDto;
import com.studyspot.backend.domain.review.entity.Review;
import com.studyspot.backend.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // 1. 리뷰 저장하기
    public Review createReview(ReviewRequestDto dto) {
        Review review = Review.builder()
                .userId(dto.getUserId())
                .placeId(dto.getPlaceId())
                .content(dto.getContent())
                .rating(dto.getRating())
                .build();

        return reviewRepository.save(review);
    }

    // 2. 모든 리뷰 조회하기
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}