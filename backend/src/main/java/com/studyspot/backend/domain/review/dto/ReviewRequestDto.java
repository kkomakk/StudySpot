package com.studyspot.backend.domain.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {
    private Long userId;
    private Long placeId;
    private String content;
    private Integer rating;
}
