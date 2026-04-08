package com.studyspot.backend.domain.review.controller;

import com.studyspot.backend.domain.review.service.ReviewService;
import com.studyspot.backend.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<Void> createReview() {
        reviewService.createReview();
        return ApiResponse.ok("리뷰가 성공적으로 등록되었습니다.");
    }
}