package com.ssafy.dto.inquiry;

import lombok.Data;

@Data
public class InquiryRequest {
    private int inquiryId;
    private int userId;
    private Long aptId;
    private String content;
    private String status;
    private boolean isRead;
}
