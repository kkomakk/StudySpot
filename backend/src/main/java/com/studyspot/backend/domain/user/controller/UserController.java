package com.studyspot.backend.domain.user.controller;

import com.studyspot.backend.domain.user.dto.NicknameUpdateRequest;
import com.studyspot.backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 닉네임 변경 API
     */
    @PatchMapping("/me/nickname")
    public ResponseEntity<String> updateNickname(
            @RequestParam(name = "userId") Long userId,
            @RequestBody NicknameUpdateRequest request) {

        userService.updateNickname(userId, request);
        return ResponseEntity.ok("닉네임이 성공적으로 변경되었습니다.");
    }

    /**
     * 회원 탈퇴 API
     */
    @DeleteMapping("/me")
    public ResponseEntity<String> withdraw(@RequestParam(name = "userId") Long userId) {
        userService.withdraw(userId);
        return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
    }
}