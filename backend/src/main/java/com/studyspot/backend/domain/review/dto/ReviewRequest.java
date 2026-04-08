package com.studyspot.backend.domain.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewRequest {
    private Long userId;
    private Long placeId;
    private Integer rating;
    private String content;
}