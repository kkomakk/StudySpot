package com.studyspot.backend.global;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 모든 엔티티의 공통 매핑 정보 (생성일, 수정일)를 관리하는 추상 클래스
 */
@Getter
@MappedSuperclass // 테이블로 생성되지 않고, 상속받는 자식 엔티티에 매핑 정보만 제공
@EntityListeners(AuditingEntityListener.class) // 엔티티의 변화를 감지하여 날짜를 자동 기록
public abstract class BaseEntity {

    @CreatedDate // ERD의 created_at과 매핑 (데이터 삽입 시 자동 기록)
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // ERD의 updated_at과 매핑 (데이터 수정 시 자동 기록)
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}