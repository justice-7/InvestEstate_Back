package com.ssafy.controller;

import com.ssafy.dto.inquiry.InquiryRequest;
import com.ssafy.dto.inquiry.InquiryResponse;
import com.ssafy.service.inquiry.InquiryService;
import com.ssafy.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquiries")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InquiryController {

    private final InquiryService inquiryService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> createInquiry(@RequestBody InquiryRequest inquiryRequest) {
        if (inquiryRequest.getAptId() == null || inquiryRequest.getContent() == null) {
            return ResponseEntity.badRequest().build();
        }
        inquiryService.createInquiry(inquiryRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<InquiryResponse>> getUserInquiries() {
        Long userId = SecurityUtil.getCurrentUserId();
        List<InquiryResponse> inquiries = inquiryService.getInquiriesForUser(userId);
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/realtor")
    @PreAuthorize("hasRole('REA')")
    public ResponseEntity<List<InquiryResponse>> getRealtorInquiries() {
        List<InquiryResponse> inquiries = inquiryService.getInquiriesForRealtor();
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/realtor/unread")
    @PreAuthorize("hasRole('REA')")
    public ResponseEntity<List<InquiryResponse>> getUnreadInquiriesForRealtor() {
        List<InquiryResponse> inquiries = inquiryService.getUnreadInquiriesForRealtor();
        return ResponseEntity.ok(inquiries);
    }
}
