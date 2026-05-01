package com.studyspot.backend.domain.place.repository;

import com.studyspot.backend.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    // 카카오맵 ID(externalId)로 장소를 찾는 메서드
    Optional<Place> findByExternalId(String externalId);

    // 카테고리별 장소 검색
    List<Place> findAllByCategory(String category);

    // 특정 태그를 포함하는 장소 검색
    List<Place> findAllByTagsContaining(String tag);

    // DB 데이터를 기반으로 한 복합 필터링 쿼리
    @Query("SELECT p FROM Place p WHERE " +
                  "(:category IS NULL OR :category = '' OR p.category LIKE CONCAT('%', :category, '%')) AND " +
                  "(:minRating IS NULL OR p.averageRating >= :minRating) AND " +
                  "(:maxCongestion IS NULL OR p.currentCongestion <= :maxCongestion)")
    List<Place> findByCondition(@Param("category") String category,
                                @Param("minRating") Double minRating,
                                @Param("maxCongestion") Integer maxCongestion);

}