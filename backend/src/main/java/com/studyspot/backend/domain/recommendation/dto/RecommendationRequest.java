package com.studyspot.backend.domain.recommendation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommendationRequest {
    private String preferredPurpose;
    private String preferredTag;
    private Integer maxCongestion;
}