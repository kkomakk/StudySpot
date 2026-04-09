package com.studyspot.backend.domain.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotificationResponse {
    private Long id;
    private String content;
    private boolean isRead;
}