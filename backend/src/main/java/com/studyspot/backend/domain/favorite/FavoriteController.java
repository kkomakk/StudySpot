package com.studyspot.backend.domain.favorite;

import com.studyspot.backend.domain.favorite.dto.FavoriteRequest;
import com.studyspot.backend.domain.favorite.dto.FavoriteResponse;
import com.studyspot.backend.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping
    public ApiResponse<String> toggleFavorite(@RequestBody FavoriteRequest request) {
        favoriteService.toggleFavorite(request);
        return ApiResponse.success("즐겨찾기 상태가 변경되었습니다.");
    }

    @GetMapping("/{userId}")
    public ApiResponse<List<FavoriteResponse>> getMyFavorites(@PathVariable Long userId) {
        List<FavoriteResponse> favorites = favoriteService.getMyFavorites(userId);
        return ApiResponse.success(favorites);
    }
}