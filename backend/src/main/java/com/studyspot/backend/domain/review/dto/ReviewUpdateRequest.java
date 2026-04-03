package com.studyspot.backend.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewUpdateRequest {

    @NotBlank(message = "리뷰 내용은 절대 비워둘 수 없습니다!")
    private String content;

    @NotNull(message = "별점을 꼭 입력해 주세요!")
    @Min(value = 1, message = "별점은 최소 1점 이상이어야 합니다.")
    @Max(value = 5, message = "별점은 최대 5점까지만 가능합니다.")
    private Integer rating;
}