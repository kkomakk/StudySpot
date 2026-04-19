package com.studyspot.backend.domain.favorite.repository;

import com.studyspot.backend.domain.favorite.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    // 유저별 목록 조회
    List<Favorite> findByUserIdOrderByCreatedAtDesc(Long userId);

    // 삭제 및 조회를 위한 메서드
    Optional<Favorite> findByUserIdAndPlaceId(Long userId, Long placeId);

    // [활성화!] 찜 여부 확인 메서드
    boolean existsByUserIdAndPlaceId(Long userId, Long placeId);
}