package com.studyspot.backend.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminResponse {
    private String status;
    private String message;
}