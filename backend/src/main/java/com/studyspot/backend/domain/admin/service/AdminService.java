package com.studyspot.backend.domain.admin.service;

import com.studyspot.backend.domain.admin.dto.AdminResponse;
import com.studyspot.backend.domain.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminResponse getAdminDashboard() {
        // 여기에 어제 짜셨던 로직을 넣으시면 됩니다!
        return new AdminResponse("SUCCESS", "관리자 대시보드 데이터입니다.");
    }
}