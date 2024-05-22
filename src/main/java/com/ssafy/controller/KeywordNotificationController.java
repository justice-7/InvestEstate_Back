package com.ssafy.controller;

import com.ssafy.dto.keyword.KeywordNotification;
import com.ssafy.service.keyword.KeywordNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class KeywordNotificationController {
    private final KeywordNotificationService keywordNotificationService;

    @GetMapping("/unread")
    public List<KeywordNotification> getUnreadNotifications() {
        return keywordNotificationService.getUnreadNotifications();
    }
}
