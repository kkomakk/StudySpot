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
    // PlaceService.java 수정

    // PlaceService.java

    @Transactional
    public PlaceDetailResponse getPlaceDetail(String externalId, String name, Double lat, Double lon) {
        // 1. 우리 DB(MySQL)에 이미 있는지 확인
        return placeRepository.findByExternalId(externalId)
                .map(PlaceDetailResponse::new)
                .orElseGet(() -> {
                    // 2. DB에 없다면 프론트에서 준 '위치'와 '이름'으로 카카오 지도에서 검색
                    List<ExternalPlaceDto> searchResults = placeApiClient.searchPlacesByKeyword(name, lat, lon, 2000);

                    if (searchResults.isEmpty()) {
                        //검색 결과가 없으면 임시 저장 로직 실행
                        return saveTemporaryPlace(externalId, name, lat, lon);
                    }

                    ExternalPlaceDto ext = searchResults.get(0);

                    // 3. 카카오에서 찾은 정보를 MySQL에 저장
                    Place savedPlace = placeRepository.save(Place.builder()
                            .externalId(externalId)
                            .name(ext.name())
                            .address(ext.roadAddress())
                            .latitude(ext.latitude())
                            .longitude(ext.longitude())
                            .imageUrl("https://images.unsplash.com/photo-1497366216548-37526070297c?w=800")
                            .averageRating(4.5)
                            .currentCongestion(0)
                            .build());

                    return new PlaceDetailResponse(savedPlace);
                });
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

    private PlaceDetailResponse saveTemporaryPlace(String externalId, String name, Double lat, Double lon) {
        Place newPlace = Place.builder()
                .externalId(externalId)
                .name(name)
                .address("상세 주소 정보를 찾을 수 없습니다.")
                .latitude(lat)
                .longitude(lon)
                .imageUrl("https://images.unsplash.com/photo-1497366216548-37526070297c?w=800")
                .build();

        return new PlaceDetailResponse(placeRepository.save(newPlace));
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