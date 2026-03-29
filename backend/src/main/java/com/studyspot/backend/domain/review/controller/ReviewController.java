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

    // 리뷰 목록 조회 API (GET)
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }
}