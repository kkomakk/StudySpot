package com.studyspot.backend.domain.review.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: 나중에 User 엔티티와 연결
    @Column(name = "user_id")
    private Long userId;

    // TODO: 나중에 Place 엔티티와 연결
    @Column(name = "place_id")
    private Long placeId;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Integer rating;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // 리뷰 수정 메서드
    public void update(String content, Integer rating) {
        this.content = content;
        this.rating = rating;
    }
}