package com.studyspot.backend.domain.notification.controller;

import com.studyspot.backend.domain.notification.dto.NotificationResponse;
import com.studyspot.backend.domain.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/{userId}")
    public List<NotificationResponse> getNotifications(@PathVariable Long userId) {
        return notificationService.getNotifications(userId);
    }
}