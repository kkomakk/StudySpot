package com.studyspot.backend.domain.review.repository;

import com.studyspot.backend.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 1. 마이페이지 - 특정 유저가 작성한 리뷰 총 개수 조회
    long countByUserId(Long userId);

    // 2. 마이페이지 - 특정 유저가 작성한 리뷰 목록을 최신순으로 조회
    List<Review> findByUserIdOrderByCreatedAtDesc(Long userId);

    // 3. 장소 상세페이지 - 특정 장소(카카오맵 ID)에 달린 리뷰 목록을 최신순으로 조회
    List<Review> findByPlace_ExternalIdOrderByCreatedAtDesc(String externalId);
}