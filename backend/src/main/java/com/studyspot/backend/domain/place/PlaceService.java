package com.studyspot.backend.domain.place;

import com.studyspot.backend.domain.place.dto.PlaceDetailResponse;
import com.studyspot.backend.domain.place.dto.PlaceResponse;
import com.studyspot.backend.domain.place.dto.PlaceSearchCondition;
import com.studyspot.backend.global.exception.CustomException;
import com.studyspot.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.studyspot.backend.external.placeapi.PlaceApiClient;
import com.studyspot.backend.external.placeapi.dto.ExternalPlaceDto;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceApiClient placeApiClient;

    /**
     * 1. 전체 목록 조회
     */
    public List<PlaceResponse> getAllPlaces() {
        return placeRepository.findAll().stream()
                .map(PlaceResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * 2. 상세 조회
     */
    public PlaceDetailResponse getPlaceDetail(Long id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.PLACE_NOT_FOUND));
        return new PlaceDetailResponse(place);
    }

    /**
     * 3. 카테고리별 필터링
     */
    public List<PlaceResponse> getPlacesByCategory(String category) {
        return placeRepository.findAllByCategory(category).stream()
                .map(PlaceResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * 4. 태그 기반 검색
     */
    public List<PlaceResponse> getPlacesByTag(String tag) {
        return placeRepository.findAllByTagsContaining(tag).stream()
                .map(PlaceResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * 5. 복합 조건 검색
     */
    public List<PlaceResponse> searchByCondition(PlaceSearchCondition condition) {
        return placeRepository.findAll().stream()
                .filter(place -> filterByCategory(place, condition.getCategory()))
                .filter(place -> filterByTag(place, condition.getTag()))
                .filter(place -> filterByRating(place, condition.getMinRating()))
                .filter(place -> filterByCongestion(place, condition.getMaxCongestion()))
                .map(PlaceResponse::new)
                .collect(Collectors.toList());
    }

    // --- 내부 필터링 메서드 ---
    private boolean filterByCategory(Place place, String category) {
        if (!StringUtils.hasText(category)) return true;
        return place.getCategory().equalsIgnoreCase(category);
    }

    private boolean filterByTag(Place place, String tag) {
        if (!StringUtils.hasText(tag)) return true;
        return place.getTags().contains(tag);
    }

    private boolean filterByRating(Place place, Double minRating) {
        if (minRating == null) return true;
        return place.getAverageRating() >= minRating;
    }

    private boolean filterByCongestion(Place place, Integer maxCongestion) {
        if (maxCongestion == null) return true;
        return place.getCurrentCongestion() <= maxCongestion;
    }

    //카카오 API를 통해 내 주변 장소 검색
    public List<ExternalPlaceDto> getNearbyPlacesFromKakao(String keyword, Double lat, Double lng, int radius) {
        return placeApiClient.searchPlacesByKeyword(keyword, lat, lng, radius);
    }
}