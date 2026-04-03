package com.studyspot.backend.domain.review.controller;

import com.studyspot.backend.domain.review.dto.ReviewRequestDto;
import com.studyspot.backend.domain.review.dto.ReviewUpdateRequest;
import com.studyspot.backend.domain.review.entity.Review;
import com.studyspot.backend.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 📝 리뷰 작성 API (POST)
    @PostMapping
    public Review createReview(@RequestBody ReviewRequestDto requestDto) {
        return reviewService.createReview(requestDto);
    }

    // 🔍 리뷰 목록 조회 API (GET)
    @GetMapping
    public List<Review> getReviews() {
        return reviewService.getReviews();
    }

    // ✏️ 리뷰 수정 API (PUT)
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(
            @PathVariable Long reviewId,
            @Valid @RequestBody ReviewUpdateRequest requestDto) { // 👈 @Valid 추가!

        reviewService.updateReview(reviewId, requestDto);
        return ResponseEntity.ok("리뷰 수정 완료!");
    }

    // 🗑️ 리뷰 삭제 API (DELETE)
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {

        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("리뷰 삭제 완료!");
    }
}