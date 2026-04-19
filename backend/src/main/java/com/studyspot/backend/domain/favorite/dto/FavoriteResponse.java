package com.studyspot.backend.domain.favorite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class FavoriteResponse {
    private Long id;
    private Long placeId;
    private String placeName;
    private String address;
    private LocalDateTime createdAt;
}