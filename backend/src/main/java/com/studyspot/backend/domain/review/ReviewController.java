package com.studyspot.backend.domain.review;

import com.studyspot.backend.domain.review.dto.ReviewRequest;
import com.studyspot.backend.domain.review.dto.ReviewResponse;
import com.studyspot.backend.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<Long> createReview(@RequestBody ReviewRequest request) {
        Long reviewId = reviewService.createReview(request);
        return ApiResponse.success(reviewId);
    }

    @GetMapping("/{placeId}")
    public ApiResponse<List<ReviewResponse>> getReviews(@PathVariable Long placeId) {
        List<ReviewResponse> reviews = reviewService.getReviews(placeId);
        return ApiResponse.success(reviews);
    }
}