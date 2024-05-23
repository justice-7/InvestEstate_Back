package com.ssafy.controller;

import com.ssafy.dto.favorite.Favorite;
import com.ssafy.service.favorite.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "*")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping("/{aptId}")
    public void addFavorite(@PathVariable Long aptId) {
        favoriteService.addFavorite(aptId);
    }

    @DeleteMapping("/{aptId}")
    public void removeFavorite(@PathVariable Long aptId) {
        favoriteService.removeFavorite(aptId);
    }

    @GetMapping
    public List<Favorite> getFavorites() {
        return favoriteService.getFavorites();
    }

    @GetMapping("/{aptId}")
    public boolean checkIfFavorite(@PathVariable Long aptId) {
        return favoriteService.isFavorite(aptId);
    }
}
