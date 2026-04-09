package com.studyspot.backend.domain.mypage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mypage")
public class MyPageController {

    @GetMapping
    public String getMyPage() {
        return "MyPage Data";
    }
}