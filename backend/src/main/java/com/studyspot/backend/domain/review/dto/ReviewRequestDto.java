package com.studyspot.backend.domain.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {
    private Long userId;
    private String externalId;
    private Long placeId;
    private String placeName;
    private String placeAddress;
    private String content;
    private Integer rating;
    private String name;
    private String address;

    public ReviewRequestDto(Long userId, String externalId, String content, int rating) {
        this.userId = userId;
        this.externalId = externalId;
        this.content = content;
        this.rating = rating;
    }

}

