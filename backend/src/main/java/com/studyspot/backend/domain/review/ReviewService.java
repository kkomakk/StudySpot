package com.studyspot.backend.domain.review;

import com.studyspot.backend.domain.review.dto.ReviewRequest;
import com.studyspot.backend.domain.review.dto.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public Long createReview(ReviewRequest request) {
        Review review = Review.builder()
                .userId(request.getUserId())
                .placeId(request.getPlaceId())
                .rating(request.getRating())
                .content(request.getContent())
                .build();

        return reviewRepository.save(review).getId();
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviews(Long placeId) {
        return reviewRepository.findAllByPlaceId(placeId).stream()
                .map(ReviewResponse::from)
                .collect(Collectors.toList());
    }
}