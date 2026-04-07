package com.studyspot.backend.external.placeapi;

import com.studyspot.backend.external.placeapi.dto.ExternalPlaceDto;
import java.util.List;

public interface PlaceApiClient {
    /**
     * 특정 좌표를 기준으로 반경 내의 키워드 장소를 검색
     *
     * @param keyword 검색어
     * @param latitude 위도 
     * @param longitude 경도
     * @param radius 검색 반경 (미터 단위, 최대 20000)
     * @return 표준화된 장소 DTO 리스트
     */
    List<ExternalPlaceDto> searchPlacesByKeyword(String keyword, Double latitude, Double longitude, int radius);
}