package com.studyspot.backend.external.placeapi.dto;

public record ExternalPlaceDto(
        String externalId, String name, String address, String roadAddress,
        String phone, Double latitude, Double longitude, String placeUrl, Double distance
) {
    public static ExternalPlaceDto fromKakaoDocument(KakaoSearchResponse.Document doc) {
        return new ExternalPlaceDto(
                doc.id(),
                doc.placeName(),
                doc.addressName(),
                doc.roadAddressName(),
                doc.phone(),
                Double.parseDouble(doc.y()),
                Double.parseDouble(doc.x()),
                doc.placeUrl(),
                (doc.distance() != null && !doc.distance().isEmpty()) ? Double.parseDouble(doc.distance()) : 0.0
        );
    }
}