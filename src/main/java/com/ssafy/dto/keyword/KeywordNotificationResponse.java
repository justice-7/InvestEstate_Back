package com.ssafy.dto.keyword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeywordNotificationResponse {
    private String notificationDate;
    private String aptDealName;
}
