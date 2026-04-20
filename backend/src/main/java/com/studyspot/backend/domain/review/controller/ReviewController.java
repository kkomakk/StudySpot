package com.studyspot.backend.domain.review.controller;

import com.studyspot.backend.domain.review.dto.ReviewRequestDto;
import com.studyspot.backend.domain.review.entity.Review;
import com.studyspot.backend.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성 API (POST)
    @PostMapping
    public Review createReview(@RequestBody ReviewRequestDto requestDto) {
        return reviewService.createReview(requestDto);
    }

    // 리뷰 전체 목록 조회 API (GET)
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // 특정 유저의 리뷰 총 개수 조회 API (GET)
    @GetMapping("/user/{userId}/count")
    public long getUserReviewCount(@PathVariable Long userId) {
        return reviewService.getUserReviewCount(userId);
    }

    // 특정 유저의 리뷰 목록 최신순 조회 API (GET)
    @GetMapping("/user/{userId}")
    public List<Review> getUserReviews(@PathVariable Long userId) {
        return reviewService.getUserReviews(userId);
    }

    // 특정 장소(카카오맵 ID)의 리뷰 목록 최신순 조회 API (GET)
    @GetMapping("/place/{externalId}")
    public List<Review> getPlaceReviews(@PathVariable String externalId) {
        return reviewService.getPlaceReviews(externalId);
    }
}