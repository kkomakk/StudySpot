package com.studyspot.backend.domain.review.service;

import com.studyspot.backend.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public void createReview() {
        // ...
    }

    // TODO: 리뷰 조회 로직 (Read)
    public void getReviews() {
        // ...
    }
}