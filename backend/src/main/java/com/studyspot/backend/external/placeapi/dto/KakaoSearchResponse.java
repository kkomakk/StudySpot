package com.studyspot.backend.external.placeapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record KakaoSearchResponse(
        List<Document> documents,
        Meta meta
) {
    public record Document(
            String id,
            @JsonProperty("place_name") String placeName,
            @JsonProperty("category_name") String categoryName,
            String phone,
            @JsonProperty("address_name") String addressName,
            @JsonProperty("road_address_name") String roadAddressName,
            @JsonProperty("x") String x,
            @JsonProperty("y") String y,
            @JsonProperty("place_url") String placeUrl,
            String distance
    ) {}

    public record Meta(
            @JsonProperty("is_end") boolean isEnd,
            @JsonProperty("pageable_count") int pageableCount,
            @JsonProperty("total_count") int totalCount
    ) {}
}