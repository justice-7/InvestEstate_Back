package com.ssafy.service.keyword;

import com.ssafy.dao.keyword.KeywordNotificationDao;
import com.ssafy.dto.keyword.KeywordNotificationResponse;
import com.ssafy.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KeywordNotificationService {
    private final KeywordNotificationDao keywordNotificationDao;

    public List<KeywordNotificationResponse> getUnreadNotifications() {
        Long userId = SecurityUtil.getCurrentUserId();
        return keywordNotificationDao.findUnreadNotificationsWithDealNameByUserId(userId);
    }

    public List<KeywordNotificationResponse> getAllNotifications() {
        Long userId = SecurityUtil.getCurrentUserId();
        return keywordNotificationDao.findAllNotificationsWithDealNameByUserId(userId);
    }

    @Transactional
    public void markAllAsRead() {
        Long userId = SecurityUtil.getCurrentUserId();
        keywordNotificationDao.markAllAsReadByUserId(userId);
    }
}
