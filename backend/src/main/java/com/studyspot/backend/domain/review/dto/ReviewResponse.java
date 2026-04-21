package com.studyspot.backend.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ReviewResponse {
    private Long id;
    private String content;
    private int rating;
    private String placeName;
    private String externalId;
    private LocalDateTime createdAt;
}