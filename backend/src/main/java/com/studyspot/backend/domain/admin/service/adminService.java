package com.studyspot.backend.domain.admin.service;

import com.studyspot.backend.domain.admin.dto.AdminResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    @Transactional(readOnly = true)
    public AdminResponse getAdminDashboard() {
        return AdminResponse.builder()
                .status("운영 중")
                .totalUsers(0) // 더미 데이터
                .build();
    }
}