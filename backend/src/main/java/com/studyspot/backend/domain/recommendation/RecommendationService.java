package com.studyspot.backend.domain.recommendation;

import com.studyspot.backend.domain.place.entity.Place;
import com.studyspot.backend.domain.place.repository.PlaceRepository;
import com.studyspot.backend.domain.recommendation.dto.RecommendationRequest;
import com.studyspot.backend.domain.recommendation.dto.RecommendationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommendationService {

    private final PlaceRepository placeRepository;
    private final RecommendationEngine recommendationEngine;

    public List<RecommendationResponse> getRecommendations(RecommendationRequest request) {
        List<Place> allPlaces = placeRepository.findAll();

        return allPlaces.stream()
                .map(place -> new RecommendationResponse(place, recommendationEngine.calculateScore(place, request)))
                .sorted((a, b) -> Double.compare(b.getScore(), a.getScore())) // 점수 높은 순 정렬
                .limit(10) // 상위 10개
                .collect(Collectors.toList());
    }
}