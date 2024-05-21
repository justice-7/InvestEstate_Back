package com.ssafy.service.keyword;

import com.ssafy.dao.keyword.KeywordNotificationDao;
import com.ssafy.dto.keyword.KeywordNotification;
import com.ssafy.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class KeywordNotificationService {
    private final KeywordNotificationDao keywordNotificationDao;

    @Transactional
    public List<KeywordNotification> getUnreadNotifications() {
        Long userId = SecurityUtil.getCurrentUserId();
        List<KeywordNotification> notifications = keywordNotificationDao.findUnreadNotificationsByUserId(userId);
        keywordNotificationDao.markAllAsReadByUserId(userId);
        return notifications;
    }
}
