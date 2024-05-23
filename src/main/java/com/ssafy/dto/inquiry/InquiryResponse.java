package com.ssafy.dto.inquiry;

import lombok.Data;

@Data
public class InquiryResponse {
    private Long inquiryId;
    private Long userId;
    private Long aptId;
    private String content;
    private String status;
    private boolean isRead;
}
