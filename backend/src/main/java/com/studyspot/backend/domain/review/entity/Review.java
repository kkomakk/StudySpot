package com.studyspot.backend.domain.review.entity;

import com.studyspot.backend.domain.place.entity.Place;
import com.studyspot.backend.domain.user.entity.User;
import com.studyspot.backend.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.Assert;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "reviews")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer rating;

    @Builder
    public Review(User user, Place place, String content, Integer rating) {
        Assert.notNull(user, "사용자 정보는 필수입니다.");
        Assert.notNull(place, "장소 정보는 필수입니다.");
        Assert.hasText(content, "리뷰 내용은 필수입니다.");
        Assert.isTrue(rating >= 1 && rating <= 5, "평점은 1점에서 5점 사이여야 합니다.");

        this.user = user;
        this.place = place;
        this.content = content;
        this.rating = rating;
    }

    public void updateReview(String content, int rating) {
        this.content = content;
        this.rating = rating;
    }

}