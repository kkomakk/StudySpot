package com.studyspot.backend.domain.notification.entity;

import com.studyspot.backend.domain.user.entity.User;
import com.studyspot.backend.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notifications")
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 500)
    private String message;

    @Builder.Default
    @Column(nullable = false)
    private boolean isRead = false;

    public void markAsRead() {
        this.isRead = true;
    }
}