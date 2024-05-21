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
    private String notificationDate;
    private boolean isRead;
}
