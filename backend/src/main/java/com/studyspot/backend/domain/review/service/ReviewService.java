package com.studyspot.backend.domain.review.service;

import com.studyspot.backend.domain.review.dto.ReviewRequestDto;
import com.studyspot.backend.domain.review.dto.ReviewResponse;
import com.studyspot.backend.domain.review.entity.Review;
import com.studyspot.backend.domain.review.repository.ReviewRepository;
import com.studyspot.backend.domain.place.entity.Place;
import com.studyspot.backend.domain.place.repository.PlaceRepository;
import com.studyspot.backend.domain.user.entity.User;
import com.studyspot.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    @Transactional
    public ReviewResponse createReview(ReviewRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        Place place = placeRepository.findByExternalId(requestDto.getExternalId())
                .orElseGet(() -> {
                    Place newPlace = Place.builder()
                            .externalId(requestDto.getExternalId())
                            .name(requestDto.getName())
                            .address(requestDto.getAddress())
                            .reviewCount(0)
                            .averageRating(0.0)
                            .build();
                    return placeRepository.save(newPlace);
                });

        place.updateStatistics(requestDto.getRating());

        Review review = Review.builder()
                .content(requestDto.getContent())
                .rating(requestDto.getRating())
                .user(user)
                .place(place)
                .build();

        Review saved = reviewRepository.save(review);
        return convertToResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> getUserReviews(Long userId) {
        return reviewRepository.findByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public long getUserReviewCount(Long userId) {
        return reviewRepository.countByUserId(userId);
    }

    private ReviewResponse convertToResponse(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .placeName(review.getPlace().getName())
                .externalId(review.getPlace().getExternalId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    @Transactional
    public void deleteReview(Long reviewId, Long userId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 없습니다."));

        if (!review.getUser().getId().equals(userId)) {
            throw new IllegalStateException("삭제 권한이 없습니다.");
        }

        Place place = review.getPlace();
        place.decreaseStatistics(review.getRating());

        reviewRepository.delete(review);
    }

    @Transactional
    public ReviewResponse updateReview(Long reviewId, Long userId, ReviewRequestDto requestDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다."));

        if (!review.getUser().getId().equals(userId)) {
            throw new IllegalStateException("수정 권한이 없습니다.");
        }

        Integer oldRating = review.getRating();
        Integer newRating = requestDto.getRating();

        if (!oldRating.equals(newRating)) {
            Place place = review.getPlace();
            place.updateReviewRating(oldRating, newRating);
        }

        review.updateReview(requestDto.getContent(), newRating);

        return convertToResponse(review);
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> getPlaceReviews(String externalId) {
        List<Review> reviews = reviewRepository.findByPlaceExternalIdOrderByCreatedAtDesc(externalId);

        return reviews.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

}