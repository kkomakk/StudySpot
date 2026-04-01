package com.studyspot.backend.domain.place.dto;

import com.studyspot.backend.domain.place.Place;
import lombok.Getter;

import java.util.List;

@Getter
public class PlaceResponse {
    private final Long id;
    private final String name;
    private final String address;
    private final Double averageRating;
    private final Integer currentCongestion;
    private final List<String> tags;

    public PlaceResponse(Place place) {
        this.id = place.getId();
        this.name = place.getName();
        this.address = place.getAddress();
        this.averageRating = place.getAverageRating();
        this.currentCongestion = place.getCurrentCongestion();
        this.tags = place.getTags();
    }
}