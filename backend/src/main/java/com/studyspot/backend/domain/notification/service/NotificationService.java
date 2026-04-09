package com.studyspot.backend.domain.notification.service;

import com.studyspot.backend.domain.notification.dto.NotificationResponse;
import com.studyspot.backend.domain.notification.entity.Notification;
import com.studyspot.backend.domain.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public List<NotificationResponse> getNotifications(Long userId) {
        return notificationRepository.findByUserIdOrderByIdDesc(userId).stream()
                .map(n -> new NotificationResponse(n.getId(), n.getMessage(), n.getCreatedAt()))
                .collect(Collectors.toList());
    }
}