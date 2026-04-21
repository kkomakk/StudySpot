package com.studyspot.backend.domain.place.repository;

import com.studyspot.backend.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    // 카카오맵 ID(externalId)로 장소를 찾는 메서드
    Optional<Place> findByExternalId(String externalId);

    // 카테고리별 장소 검색
    List<Place> findAllByCategory(String category);

    // 특정 태그를 포함하는 장소 검색
    List<Place> findAllByTagsContaining(String tag);


}