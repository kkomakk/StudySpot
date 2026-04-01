package com.studyspot.backend.domain.place;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    // 카테고리별 장소 검색
    List<Place> findAllByCategory(String category);

    // 특정 태그를 포함하는 장소 검색
    List<Place> findAllByTagsContaining(String tag);
}