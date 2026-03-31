package com.studyspot.backend.global.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 모든 API 응답을 동일한 형식으로 내려주기 위한 공통 응답 객체
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;

    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    public static ApiResponse<Void> ok(String message) {
        return new ApiResponse<>(true, message, null);
    }

    public static ApiResponse<Void> fail(String message) {
        return new ApiResponse<>(false, message, null);
    }
}