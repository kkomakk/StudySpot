package com.studyspot.backend.domain.place.controller;

import com.studyspot.backend.domain.place.service.PlaceService;
import com.studyspot.backend.domain.place.service.PlaceViewService;
import com.studyspot.backend.domain.place.dto.PlaceDetailResponse;
import com.studyspot.backend.domain.place.dto.PlaceResponse;
import com.studyspot.backend.domain.place.dto.PlaceSearchCondition;
import com.studyspot.backend.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/places")
public class PlaceController {

    private final PlaceService placeService;
    private final PlaceViewService placeViewService;

    /**
     * 1. 전체 목록 조회
     */
    @GetMapping
    public ApiResponse<List<PlaceResponse>> getPlaces() {
        return ApiResponse.ok("장소 목록 조회 성공", placeService.getAllPlaces());
    }

    /**
     * 2. 상세 조회
     */
    @GetMapping("/{id}")
    public ApiResponse<PlaceDetailResponse> getPlaceDetail(
            @PathVariable String id,
            @RequestParam String name,
            @RequestParam Double lat,
            @RequestParam(name = "lng") Double lon
    ) {
        System.out.println("컨트롤러로부터 받은 데이터 -> 이름: " + name + ", 위도: " + lat + ", 경도: " + lon);
        return ApiResponse.ok("장소 상세 조회 성공", placeService.getPlaceDetail(id, name, lat, lon));
    }

    /**
     * 3. 카테고리별 필터링
     */
    @GetMapping("/category/{category}")
    public ApiResponse<List<PlaceResponse>> getPlacesByCategory(@PathVariable String category) {
        return ApiResponse.ok(category + " 카테고리 조회 성공", placeService.getPlacesByCategory(category));
    }

    /**
     * 4. 태그 기반 검색
     */
    @GetMapping("/search/tag")
    public ApiResponse<List<PlaceResponse>> searchByTag(@RequestParam String tag) {
        return ApiResponse.ok(tag + " 태그 검색 성공", placeService.getPlacesByTag(tag));
    }

    /**
     * 5. 복합 조건 검색
     */
    @GetMapping("/search")
    public ApiResponse<List<PlaceResponse>> searchByCondition(PlaceSearchCondition condition) {
        return ApiResponse.ok("조건 검색 성공", placeService.searchByCondition(condition));
    }

    // 내 위치 주변 검색 요청을 받는 API
    @GetMapping("/nearby")
    public ApiResponse<List<PlaceResponse>> getNearbyPlaces(
            @RequestParam(defaultValue = "스터디룸") String keyword,
            @RequestParam Double lat,
            @RequestParam Double lng,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        return ApiResponse.ok(
                "주변 스터디룸 검색 성공",
                placeService.getNearbyPlacesFromKakao(keyword, lat, lng, radius)
        );
    }

    /**
     * 6. 특정 장소 시청자 수 업데이트 및 조회
     */
    @PostMapping("/{placeId}/viewing")
    public ApiResponse<Integer> updateViewingCount(
            @PathVariable Long placeId,
            @RequestParam String sessionId) {

        placeViewService.addViewer(placeId, sessionId);
        int currentViewers = placeViewService.getViewerCount(placeId);

        return ApiResponse.ok("시청자 수 업데이트 성공", currentViewers);
    }

    /**
     * 7. 특정 장소 시청자 수 즉시 차감
     */
    @PostMapping("/{placeId}/viewing/leave")
    public ApiResponse<Void> removeViewingCount(
            @PathVariable Long placeId,
            @RequestParam String sessionId) {

        placeViewService.removeViewer(placeId, sessionId);
        return ApiResponse.ok("시청자 수 차감 성공", null);
    }

}