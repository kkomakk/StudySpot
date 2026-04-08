package com.studyspot.backend.domain.notification;

import com.studyspot.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "notifications")
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId; // 알림을 받을 유저

    @Column(nullable = false)
    private String message; // 알림 내용

    @Column(nullable = false)
    private boolean isRead; // 읽음 여부
}