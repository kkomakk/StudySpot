package com.studyspot.backend.domain.review.service;

import com.studyspot.backend.domain.review.dto.ReviewRequestDto;
import com.studyspot.backend.domain.review.dto.ReviewUpdateRequest;
import com.studyspot.backend.domain.review.entity.Review;
import com.studyspot.backend.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // 📝 리뷰 작성 비즈니스 로직
    @Transactional
    public Review createReview(ReviewRequestDto requestDto) {
        // (기존에 작성하셨던 생성 로직 그대로 유지)
        Review review = new Review();
        review.update(requestDto.getContent(), requestDto.getRating());
        return reviewRepository.save(review);
    }

    // 🔍 리뷰 전체 조회 비즈니스 로직
    @Transactional(readOnly = true)
    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    // ✏️ 리뷰 수정 비즈니스 로직
    @Transactional
    public void updateReview(Long reviewId, ReviewUpdateRequest requestDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다. ID: " + reviewId));

        review.update(requestDto.getContent(), requestDto.getRating());
    }

    // 🗑️ 리뷰 삭제 비즈니스 로직
    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다. ID: " + reviewId));

        reviewRepository.delete(review);
    }
}