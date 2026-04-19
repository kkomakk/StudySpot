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

    // 알림 목록 조회
    @GetMapping
    public List<NotificationResponse> getNotifications(@RequestParam Long userId) {
        return notificationService.getNotifications(userId);
    }

    // 알림 읽음 처리
    @PatchMapping("/{id}/read")
    public String markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return "success";
    }
}