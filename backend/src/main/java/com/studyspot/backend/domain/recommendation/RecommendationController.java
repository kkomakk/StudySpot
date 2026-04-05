package com.studyspot.backend.domain.recommendation;

import com.studyspot.backend.domain.recommendation.dto.RecommendationRequest;
import com.studyspot.backend.domain.recommendation.dto.RecommendationResponse;
import com.studyspot.backend.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping
    public ApiResponse<List<RecommendationResponse>> getRecommendations(RecommendationRequest request) {
        List<RecommendationResponse> results = recommendationService.getRecommendations(request);
        return ApiResponse.ok("개인 맞춤형 추천 결과입니다.", results);
    }
}