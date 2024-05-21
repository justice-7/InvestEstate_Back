package com.ssafy.service.favorite;

import com.ssafy.dao.favorite.FavoriteDao;
import com.ssafy.dto.favorite.Favorite;
import com.ssafy.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FavoriteService {
    private final FavoriteDao favoriteDao;

    @Transactional
    public void addFavorite(Long aptDealId) {
        Long userId = SecurityUtil.getCurrentUserId();
        if (favoriteDao.isFavorite(userId, aptDealId) == 0) {
            favoriteDao.addFavorite(userId, aptDealId);
        }
    }

    @Transactional
    public void removeFavorite(Long aptDealId) {
        Long userId = SecurityUtil.getCurrentUserId();
        favoriteDao.removeFavorite(userId, aptDealId);
    }

    public List<Favorite> getFavorites() {
        Long userId = SecurityUtil.getCurrentUserId();
        return favoriteDao.findFavoritesByUserId(userId);
    }
}
