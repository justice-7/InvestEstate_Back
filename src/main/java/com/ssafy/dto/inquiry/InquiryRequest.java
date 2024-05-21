package com.ssafy.dto.inquiry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
public class InquiryRequest {
    private Long inquiryId;
    private Long aptDealId;
    private String message;
    private String status;
}
