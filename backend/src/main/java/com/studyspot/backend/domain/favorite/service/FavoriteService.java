package com.studyspot.backend.domain.favorite.service;

import com.studyspot.backend.domain.favorite.dto.FavoriteResponse;
import com.studyspot.backend.domain.favorite.entity.Favorite;
import com.studyspot.backend.domain.favorite.repository.FavoriteRepository;
import com.studyspot.backend.domain.place.Place;
import com.studyspot.backend.domain.place.PlaceRepository;
import com.studyspot.backend.domain.user.User;
import com.studyspot.backend.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    @Transactional(readOnly = true)
    public List<FavoriteResponse> getUserFavorites(Long userId) {
        return favoriteRepository.findByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(f -> FavoriteResponse.builder()
                        .id(f.getId())
                        .placeId(f.getPlace().getId())
                        .placeName(f.getPlace().getName())
                        .address(f.getPlace().getRoadAddress())
                        .createdAt(f.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    // 찜 여부 확인
    @Transactional(readOnly = true)
    public boolean isFavorite(Long userId, Long placeId) {
        return favoriteRepository.existsByUserIdAndPlaceId(userId, placeId);
    }

    @Transactional
    public String toggleFavorite(Long userId, String externalId) { // placeId 대신 externalId(String) 사용
        Place place = placeRepository.findByExternalId(externalId)
                .orElseThrow(() -> new IllegalArgumentException("장소를 먼저 상세 조회해주세요."));

        return favoriteRepository.findByUserIdAndPlaceId(userId, place.getId())
                .map(favorite -> {
                    favoriteRepository.delete(favorite);
                    return "deleted";
                })
                .orElseGet(() -> {
                    User user = userRepository.findById(userId).orElseThrow();
                    favoriteRepository.save(Favorite.builder().user(user).place(place).build());
                    return "added";
                });
    }
}