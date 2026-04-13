package com.studyspot.backend.domain.place.dto;

import com.studyspot.backend.domain.place.Place;
import lombok.Getter;
import java.util.List;

@Getter
public class PlaceDetailResponse {
    private final Long id;
    private final String externalId;
    private final String name;
    private final String address;
    private final String roadAddress;
    private final String phone;
    private final String placeUrl;
    private final String category;
    private final Double latitude;
    private final Double longitude;
    private final Double averageRating;
    private final Integer reviewCount;
    private final Integer currentCongestion;
    private final List<String> tags;
    private final String imageUrl;

    public PlaceDetailResponse(Place place) {
        this.id = place.getId();
        this.externalId = place.getExternalId();
        this.name = place.getName();
        this.address = place.getAddress();
        this.roadAddress = place.getRoadAddress();
        this.phone = place.getPhone();
        this.placeUrl = place.getPlaceUrl();
        this.category = place.getCategory();
        this.latitude = place.getLatitude();
        this.longitude = place.getLongitude();
        this.averageRating = place.getAverageRating();
        this.reviewCount = place.getReviewCount();
        this.currentCongestion = place.getCurrentCongestion();
        this.tags = place.getTags();
        this.imageUrl = place.getImageUrl();
    }
}