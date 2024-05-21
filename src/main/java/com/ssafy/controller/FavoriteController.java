package com.ssafy.controller;

import com.ssafy.dto.favorite.Favorite;
import com.ssafy.service.favorite.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping("/{aptDealId}")
    public void addFavorite(@PathVariable Long aptDealId) {
        favoriteService.addFavorite(aptDealId);
    }

    @DeleteMapping("/{aptDealId}")
    public void removeFavorite(@PathVariable Long aptDealId) {
        favoriteService.removeFavorite(aptDealId);
    }

    @GetMapping
    public List<Favorite> getFavorites() {
        return favoriteService.getFavorites();
    }
}
