package com.studyspot.backend.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 닉네임 수정 요청 DTO
 */
@Getter
@NoArgsConstructor
public class NicknameUpdateRequest {
    private String nickname;
}