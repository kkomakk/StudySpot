package com.studyspot.backend.domain.mypage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MyPageResponse {
    private String nickname;
    private String email;
    private String bio;
    private long reviewCount;
    private long favoriteCount;
}