package com.studyspot.backend.domain.favorite.controller;

import com.studyspot.backend.domain.favorite.dto.FavoriteResponse;
import com.studyspot.backend.domain.favorite.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @GetMapping("/{userId}")
    public List<FavoriteResponse> getUserFavorites(@PathVariable Long userId) {
        return favoriteService.getUserFavorites(userId);
    }
}