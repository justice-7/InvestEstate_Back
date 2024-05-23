package com.ssafy.controller;

import com.ssafy.dto.keyword.KeywordNotificationResponse;
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
    public List<KeywordNotificationResponse> getUnreadNotifications() {
        return keywordNotificationService.getUnreadNotifications();
    }

    @GetMapping
    public List<KeywordNotificationResponse> getAllNotifications() {
        return keywordNotificationService.getAllNotifications();
    }

    @PostMapping("/mark-all-read")
    public ResponseEntity<Void> markAllAsRead() {
        keywordNotificationService.markAllAsRead();
        return ResponseEntity.ok().build();
    }
}
