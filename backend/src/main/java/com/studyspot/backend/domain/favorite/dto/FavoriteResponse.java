package com.studyspot.backend.domain.favorite.dto;

import com.studyspot.backend.domain.favorite.Favorite;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FavoriteResponse {
    private Long id;
    private Long placeId;

    public static FavoriteResponse from(Favorite favorite) {
        return FavoriteResponse.builder()
                .id(favorite.getId())
                .placeId(favorite.getPlaceId())
                .build();
    }
}