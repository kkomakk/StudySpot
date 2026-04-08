package com.studyspot.backend.domain.favorite;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findAllByUserId(Long userId);
    Optional<Favorite> findByUserIdAndPlaceId(Long userId, Long placeId);
    void deleteByUserIdAndPlaceId(Long userId, Long placeId);
}