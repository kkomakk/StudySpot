package com.studyspot.backend.domain.mypage.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyPageResponse {
    private Long userId;
    private String nickname;

}