package com.ssafy.dto.inquiry;

import lombok.Data;

@Data
public class InquiryResponse {
    private Long inquiryId;
    private Long userId;
    private Long aptDealId;
    private String message;
    private String status;
    private boolean isRead;
}
