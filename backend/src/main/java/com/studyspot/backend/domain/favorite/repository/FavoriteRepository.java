package com.studyspot.backend.domain.favorite.repository;

import com.studyspot.backend.domain.favorite.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    // 1. 유저별 즐겨찾기 목록 조회 (최신순)
    List<Favorite> findByUserIdOrderByCreatedAtDesc(Long userId);

    // 2. 마이페이지 : 유저가 찜한 장소 총 개수
    long countByUserId(Long userId);

    // 3. 삭제 및 조회를 위한 메서드
    Optional<Favorite> findByUserIdAndPlaceId(Long userId, Long placeId);

    // 4. 찜 여부 확인 메서드
    boolean existsByUserIdAndPlaceId(Long userId, Long placeId);
}