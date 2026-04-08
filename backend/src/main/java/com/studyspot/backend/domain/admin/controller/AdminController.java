package com.studyspot.backend.domain.admin.controller;

import com.studyspot.backend.domain.admin.dto.AdminResponse;
import com.studyspot.backend.domain.admin.service.AdminService;
import com.studyspot.backend.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/dashboard")
    public ApiResponse<AdminResponse> getDashboard() {
        AdminResponse response = adminService.getAdminDashboard();
        return ApiResponse.success(response);
    }
}