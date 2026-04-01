package com.studyspot.backend.global.exception;

import lombok.Getter;

/**
 * 서비스 로직에서 사용할 커스텀 예외
 */
@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}