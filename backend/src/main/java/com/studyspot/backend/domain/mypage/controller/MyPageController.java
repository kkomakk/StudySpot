package com.studyspot.backend.domain.mypage.controller;

import com.studyspot.backend.domain.mypage.dto.MyPageResponse;
import com.studyspot.backend.domain.mypage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/{userId}")
    public ResponseEntity<MyPageResponse> getMyPage(@PathVariable Long userId) {
        return ResponseEntity.ok(myPageService.getMyPageInfo(userId));
    }
}