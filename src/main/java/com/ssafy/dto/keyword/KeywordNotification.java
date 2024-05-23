package com.ssafy.dto.keyword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeywordNotification {
    private Long notificationId;
    private Long userId;
    private Integer keywordId;
    private Long aptDealId;
    private String notificationDate;  // 추가된 필드
    private boolean isRead;
}
