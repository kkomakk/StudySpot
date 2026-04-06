package com.studyspot.backend.domain.recommendation.dto;

import com.studyspot.backend.domain.place.Place;
import lombok.Getter;

@Getter
public class RecommendationResponse {
    private final Long id;
    private final String name;
    private final String address;
    private final Integer currentCongestion;
    private final Double score;

    public RecommendationResponse(Place place, double score) {
        this.id = place.getId();
        this.name = place.getName();
        this.address = place.getAddress();
        this.currentCongestion = place.getCurrentCongestion();
        this.score = score;
    }
}