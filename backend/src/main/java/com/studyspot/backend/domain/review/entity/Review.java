package com.studyspot.backend.domain.review.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long placeId; 

    private String content;
    private int rating;

    @Builder
    public Review(Long userId, Long placeId, String content, int rating) {
        this.userId = userId;
        this.placeId = placeId;
        this.content = content;
        this.rating = rating;
    }
}