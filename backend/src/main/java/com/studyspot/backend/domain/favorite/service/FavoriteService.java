package com.studyspot.backend.domain.favorite.service;

import com.studyspot.backend.domain.favorite.dto.FavoriteResponse;
import com.studyspot.backend.domain.favorite.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    public List<FavoriteResponse> getUserFavorites(Long userId) {
        return favoriteRepository.findByUserId(userId).stream()
                .map(f -> new FavoriteResponse(f.getId(), f.getSpotId()))
                .collect(Collectors.toList());
    }
}