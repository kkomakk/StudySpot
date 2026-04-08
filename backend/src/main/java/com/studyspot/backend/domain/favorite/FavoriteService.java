package com.studyspot.backend.domain.favorite;

import com.studyspot.backend.domain.favorite.dto.FavoriteRequest;
import com.studyspot.backend.domain.favorite.dto.FavoriteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Transactional
    public void toggleFavorite(FavoriteRequest request) {
        favoriteRepository.findByUserIdAndPlaceId(request.getUserId(), request.getPlaceId())
                .ifPresentOrElse(
                        favoriteRepository::delete,
                        () -> favoriteRepository.save(Favorite.builder()
                                .userId(request.getUserId())
                                .placeId(request.getPlaceId())
                                .build())
                );
    }

    @Transactional(readOnly = true)
    public List<FavoriteResponse> getMyFavorites(Long userId) {
        return favoriteRepository.findAllByUserId(userId).stream()
                .map(FavoriteResponse::from)
                .collect(Collectors.toList());
    }
}