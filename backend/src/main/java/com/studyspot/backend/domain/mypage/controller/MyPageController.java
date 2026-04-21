package com.studyspot.backend.domain.mypage.controller;

import com.studyspot.backend.domain.mypage.dto.MyPageResponse;
import com.studyspot.backend.domain.mypage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/me")
    public ResponseEntity<MyPageResponse> getMyPageMe(Authentication authentication) {
        if (authentication == null) return ResponseEntity.status(401).build();
        return ResponseEntity.ok(myPageService.getMyPageInfoByEmail(authentication.getName()));
    }

    @GetMapping("/{userId:[0-9]+}")
    public ResponseEntity<MyPageResponse> getMyPageById(@PathVariable Long userId) {
        return ResponseEntity.ok(myPageService.getMyPageInfo(userId));
    }
}