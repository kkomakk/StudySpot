package com.studyspot.backend.domain.place.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceSearchCondition {
    private String category;
    private String tag;
    private Double minRating;
    private Integer maxCongestion;
}