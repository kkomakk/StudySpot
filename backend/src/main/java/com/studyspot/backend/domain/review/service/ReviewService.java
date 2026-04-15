package com.studyspot.backend.domain.review.service;

import com.studyspot.backend.domain.place.Place;
import com.studyspot.backend.domain.place.PlaceRepository;
import com.studyspot.backend.domain.review.dto.ReviewRequestDto;
import com.studyspot.backend.domain.review.entity.Review;
import com.studyspot.backend.domain.review.repository.ReviewRepository;
import com.studyspot.backend.domain.user.User;
import com.studyspot.backend.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    // 1. 리뷰 저장하기
    @Transactional
    public Review createReview(ReviewRequestDto dto) {


        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다. ID: " + dto.getUserId()));


        Place place = placeRepository.findById(dto.getPlaceId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 장소입니다. ID: " + dto.getPlaceId()));


        Review review = Review.builder()
                .user(user)
                .place(place)
                .content(dto.getContent())
                .rating(dto.getRating())
                .build();

        return reviewRepository.save(review);
    }


    @Transactional(readOnly = true)
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}