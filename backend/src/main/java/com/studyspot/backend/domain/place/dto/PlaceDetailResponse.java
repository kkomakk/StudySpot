package com.studyspot.backend.domain.place.dto;

import com.studyspot.backend.domain.place.Place;
import lombok.Getter;

import java.util.List;

@Getter
public class PlaceDetailResponse {
    private final Long id;
    private final String name;
    private final String address;
    private final String category;
    private final Double latitude;
    private final Double longitude;
    private final Double averageRating;
    private final Integer currentCongestion;
    private final List<String> tags;

    public PlaceDetailResponse(Place place) {
        this.id = place.getId();
        this.name = place.getName();
        this.address = place.getAddress();
        this.category = place.getCategory();
        this.latitude = place.getLatitude();
        this.longitude = place.getLongitude();
        this.averageRating = place.getAverageRating();
        this.currentCongestion = place.getCurrentCongestion();
        this.tags = place.getTags();
    }
}