package com.ssafy.controller;


import com.ssafy.dto.keyword.UserKeywordRequest;
import com.ssafy.dto.user.UserInfoResponse;
import com.ssafy.dto.user.UserUpdateRequest;
import com.ssafy.service.keyword.KeywordService;
import com.ssafy.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final KeywordService keywordService;

    @GetMapping("/info")
    public ResponseEntity<UserInfoResponse> getUserInfo(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        UserInfoResponse userInfo = userService.getUserInfo(userId);
        return ResponseEntity.ok(userInfo);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody UserUpdateRequest userUpdateRequest, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        userService.updateUser(userId, userUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/keywords")
    public ResponseEntity<List<String>> getUserKeywords(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        List<String> keywords = keywordService.getUserKeywords(userId);
        return ResponseEntity.ok(keywords);
    }
    @PutMapping("/keywords")
    public ResponseEntity<Void> setUserKeyword(@RequestBody UserKeywordRequest userKeywordRequest, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        keywordService.setUserKeyword(userId, userKeywordRequest.getKeyword());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/keywords")
    public ResponseEntity<Void> deleteUserKeyword(@RequestBody UserKeywordRequest userKeywordRequest, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        keywordService.deleteUserKeyword(userId, userKeywordRequest.getKeyword());
        return ResponseEntity.ok().build();
    }
}