package com.studyspot.backend.domain.favorite.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FavoriteResponse {
    private Long id;
    private Long spotId;
}