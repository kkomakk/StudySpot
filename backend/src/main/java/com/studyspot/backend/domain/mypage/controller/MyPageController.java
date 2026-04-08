package com.studyspot.backend.domain.mypage.controller;

import com.studyspot.backend.domain.mypage.dto.MyPageResponse;
import com.studyspot.backend.domain.mypage.service.MyPageService;
import com.studyspot.backend.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping
    public ApiResponse<MyPageResponse> getMyPage(@RequestParam Long userId) {


        MyPageResponse response = myPageService.getMyPageInfo(userId);
        return ApiResponse.success(response);
    }
}