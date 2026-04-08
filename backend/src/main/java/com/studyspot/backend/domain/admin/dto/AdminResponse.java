package com.studyspot.backend.domain.admin.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminResponse {
    private String status;
    private int totalUsers;
}