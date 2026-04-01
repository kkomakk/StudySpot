package com.studyspot.backend.domain.place;

import com.studyspot.backend.domain.place.dto.PlaceDetailResponse;
import com.studyspot.backend.domain.place.dto.PlaceResponse;
import com.studyspot.backend.domain.place.dto.PlaceSearchCondition;
import com.studyspot.backend.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * StudySpot 장소 API 컨트롤러
 */
@RestController
@RequiredArgsConstructor // 중요: 이 어노테이션이 있어야 placeService 주입이 에러 없이 작동합니다.
@RequestMapping("/api/places")
public class PlaceController {

    private final PlaceService placeService; // 중요: private final이 붙어있어야 합니다.

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
    public ApiResponse<PlaceDetailResponse> getPlaceDetail(@PathVariable Long id) {
        return ApiResponse.ok("장소 상세 조회 성공", placeService.getPlaceDetail(id));
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
}