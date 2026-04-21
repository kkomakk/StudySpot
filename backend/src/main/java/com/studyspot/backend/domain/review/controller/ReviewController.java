package com.studyspot.backend.domain.review.controller;

import com.studyspot.backend.domain.review.dto.ReviewRequestDto;
import com.studyspot.backend.domain.review.dto.ReviewResponse;
import com.studyspot.backend.domain.review.service.ReviewService;
import com.studyspot.backend.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 특정 유저의 리뷰 목록 조회
    @GetMapping("/user/{userId:[0-9]+}")
    public ApiResponse<List<ReviewResponse>> getUserReviews(@PathVariable Long userId) {
        return ApiResponse.ok("나의 리뷰 목록 조회 성공", reviewService.getUserReviews(userId));
    }

    // 새로운 리뷰 작성
    @PostMapping
    public ApiResponse<ReviewResponse> createReview(@RequestBody ReviewRequestDto requestDto) {
        ReviewResponse result = reviewService.createReview(requestDto);
        return ApiResponse.ok("리뷰가 성공적으로 등록되었습니다.", result);
    }

    // 리뷰 수정
    @PatchMapping("/{reviewId}")
    public ApiResponse<ReviewResponse> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewRequestDto requestDto,
            @RequestParam Long userId) {

        ReviewResponse result = reviewService.updateReview(reviewId, userId, requestDto);
        return ApiResponse.ok("리뷰 수정 성공", result);
    }

    // 리뷰 삭제
    @DeleteMapping("/{reviewId:[0-9]+}")
    public ApiResponse<String> deleteReview(
            @PathVariable Long reviewId,
            @RequestParam Long userId) {
        reviewService.deleteReview(reviewId, userId);
        return ApiResponse.ok("리뷰 삭제 성공", null);
    }


    // 리뷰 조회
    @GetMapping("/place/{externalId}")
    public ApiResponse<List<ReviewResponse>> getPlaceReviews(@PathVariable String externalId) {
        List<ReviewResponse> reviews = reviewService.getPlaceReviews(externalId);
        return ApiResponse.ok("장소별 리뷰 조회 성공", reviews);
    }

}