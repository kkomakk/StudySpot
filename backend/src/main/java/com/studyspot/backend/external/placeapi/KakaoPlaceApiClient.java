package com.studyspot.backend.external.placeapi;

import com.studyspot.backend.external.placeapi.dto.ExternalPlaceDto;
import com.studyspot.backend.external.placeapi.dto.KakaoSearchResponse;
import com.studyspot.backend.global.exception.CustomException;
import com.studyspot.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoPlaceApiClient implements PlaceApiClient {

    private final RestTemplate restTemplate;

    @Value("${kakao.rest-api-key}")
    private String kakaoRestApiKey;

    private static final String KAKAO_LOCAL_SEARCH_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";

    @Override
    public List<ExternalPlaceDto> searchPlacesByKeyword(String keyword, Double latitude, Double longitude, int radius) {
        try {
            URI uri = UriComponentsBuilder.fromHttpUrl(KAKAO_LOCAL_SEARCH_URL)
                    .queryParam("query", keyword)
                    .queryParam("y", latitude)
                    .queryParam("x", longitude)
                    .queryParam("radius", radius)
                    .queryParam("sort", "distance") // 거리순 정렬
                    .build()
                    .encode()
                    .toUri();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "KakaoAK " + kakaoRestApiKey);
            HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

            ResponseEntity<KakaoSearchResponse> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    httpEntity,
                    KakaoSearchResponse.class
            );

            if (response.getBody() == null || response.getBody().documents() == null) {
                return Collections.emptyList();
            }

            // 카카오 JSON 데이터를 우리 시스템의 범용 DTO로 변환하여 반환
            return response.getBody().documents().stream()
                    .map(ExternalPlaceDto::fromKakaoDocument)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("카카오 장소 API 호출 중 에러 발생: {}", e.getMessage(), e);
            // 글로벌 예외 처리기로 에러를 넘겨서 프론트엔드에 깔끔한 에러 응답
            e.printStackTrace();
            throw new CustomException(ErrorCode.EXTERNAL_API_ERROR);
        }
    }
}