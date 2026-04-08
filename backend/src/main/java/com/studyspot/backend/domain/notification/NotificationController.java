package com.studyspot.backend.domain.notification;

import com.studyspot.backend.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ApiResponse<List<Notification>> getNotifications(@RequestParam Long userId) {
        List<Notification> notifications = notificationService.getMyNotifications(userId);
        return ApiResponse.success(notifications);
    }
}