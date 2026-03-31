package com.studyspot.backend.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    /**
     * 스프링 시큐리티 Role 포맷 준수
     */
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String key;
}