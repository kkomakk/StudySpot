package com.studyspot.backend.domain.favorite.controller;

import com.studyspot.backend.domain.favorite.dto.FavoriteResponse;
import com.studyspot.backend.domain.favorite.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    // 즐겨찾기 목록 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteResponse>> getUserFavorites(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteService.getUserFavorites(userId));
    }

    // 찜 여부 확인 API
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkFavorite(@RequestParam Long userId, @RequestParam Long placeId) {
        return ResponseEntity.ok(favoriteService.isFavorite(userId, placeId));
    }

    // 찜하기/취소 토글
    @PostMapping("/toggle")
    public ResponseEntity<String> toggleFavorite(@RequestParam Long userId, @RequestParam String placeId) {
        return ResponseEntity.ok(favoriteService.toggleFavorite(userId, placeId));
    }
}