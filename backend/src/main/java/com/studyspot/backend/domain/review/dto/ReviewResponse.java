package com.studyspot.backend.domain.review.dto;

import com.studyspot.backend.domain.review.Review;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewResponse {
    private Long id;
    private Long userId;
    private Long placeId;
    private Integer rating;
    private String content;

    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .userId(review.getUserId())
                .placeId(review.getPlaceId())
                .rating(review.getRating())
                .content(review.getContent())
                .build();
    }
}