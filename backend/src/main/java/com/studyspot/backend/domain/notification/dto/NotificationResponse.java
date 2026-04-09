package com.studyspot.backend.domain.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class NotificationResponse {
    private Long id;
    private String message;
    private LocalDateTime createdAt; // 이 타입이 서비스와 일치해야 합니다.
}