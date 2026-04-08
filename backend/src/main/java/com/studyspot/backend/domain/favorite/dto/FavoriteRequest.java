package com.studyspot.backend.domain.favorite.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FavoriteRequest {
    private Long userId;
    private Long placeId;
}