package com.studyspot.backend.domain.place.dto;

import com.studyspot.backend.domain.place.Place;
import com.studyspot.backend.external.placeapi.dto.ExternalPlaceDto;
import lombok.Getter;
import java.util.List;

@Getter
public class PlaceResponse {
    private final Long id;
    private final String externalId;
    private final String name;
    private final String address;
    private final String roadAddress;
    private final String phone;
    private final Double averageRating;
    private final Integer reviewCount;
    private final Integer currentCongestion;
    private final List<String> tags;
    private final Double latitude;
    private final Double longitude;
    private final Double distance;

    /**
     * 1. 엔티티로부터 생성
     */
    public PlaceResponse(Place place) {
        this.id = place.getId();
        this.externalId = place.getExternalId();
        this.name = place.getName();
        this.address = place.getAddress();
        this.roadAddress = place.getRoadAddress();
        this.phone = place.getPhone();
        this.averageRating = place.getAverageRating();
        this.reviewCount = place.getReviewCount();
        this.currentCongestion = place.getCurrentCongestion();
        this.tags = place.getTags();
        this.latitude = place.getLatitude();
        this.longitude = place.getLongitude();
        this.distance = 0.0;
    }

    /**
     * 2. 카카오 검색 결과 + DB 별점 병합용 생성자
     */
    public PlaceResponse(ExternalPlaceDto ext, Double averageRating, Integer reviewCount) {
        this.id = null;
        this.externalId = ext.externalId();
        this.name = ext.name();
        this.address = ext.address();
        this.roadAddress = ext.roadAddress();
        this.phone = ext.phone();
        this.averageRating = (averageRating != null) ? averageRating : 0.0;
        this.reviewCount = (reviewCount != null) ? reviewCount : 0;
        this.currentCongestion = 0;
        this.tags = java.util.Collections.emptyList();
        this.latitude = ext.latitude();
        this.longitude = ext.longitude();
        this.distance = ext.distance();
    }
}