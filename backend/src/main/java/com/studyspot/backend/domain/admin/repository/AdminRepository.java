package com.studyspot.backend.domain.admin.repository;

import com.studyspot.backend.domain.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    // 기본 CRUD 기능이 포함되어 있습니다.
}