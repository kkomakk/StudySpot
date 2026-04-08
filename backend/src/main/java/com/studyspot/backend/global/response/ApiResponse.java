package com.studyspot.backend.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private final String message;
    private final T data;

    // 데이터가 있는 성공 응답
    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<>(message, data);
    }

    // 데이터가 없는 성공 응답 (메시지만 띄울 때)
    public static <T> ApiResponse<T> ok(String message) {
        return new ApiResponse<>(message, null);
    }
}