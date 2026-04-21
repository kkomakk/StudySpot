package com.studyspot.backend.domain.recommendation;

import com.studyspot.backend.domain.place.entity.Place;
import com.studyspot.backend.domain.recommendation.dto.RecommendationRequest;
import org.springframework.stereotype.Component;

@Component
public class RecommendationEngine {

    public double calculateScore(Place place, RecommendationRequest request) {
        double score = 0.0;

        // 1. 별점 가중치
        score += place.getAverageRating() * 2.0;

        // 2. 혼잡도 가중치
        // 0:여유(+10점), 1:보통(+5점), 2:혼잡(0점)
        if (place.getCurrentCongestion() == 0) score += 10.0;
        else if (place.getCurrentCongestion() == 1) score += 5.0;

        // 3. 사용자 취향 일치도
        if (request.getPreferredTag() != null && place.getTags().contains(request.getPreferredTag())) {
            score += 15.0;
        }

        return score;
    }
}