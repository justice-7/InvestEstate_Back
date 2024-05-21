package com.ssafy.service.inquiry;

import com.ssafy.dao.inquiry.InquiryDao;
import com.ssafy.dto.inquiry.InquiryRequest;
import com.ssafy.dto.inquiry.InquiryResponse;
import com.ssafy.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryDao inquiryDao;

    @Transactional
    public void createInquiry(InquiryRequest inquiryRequest) {
        Long userId = SecurityUtil.getCurrentUserId();
        inquiryDao.save(userId, inquiryRequest);
    }

    public List<InquiryResponse> getInquiriesForUser() {
        Long userId = SecurityUtil.getCurrentUserId();
        return inquiryDao.findByUserId(userId);
    }

    public List<InquiryResponse> getInquiriesForRealtor() {
        Long realtorId = SecurityUtil.getCurrentUserId();
        return inquiryDao.findByRealtorId(realtorId);
    }

    @Transactional
    public List<InquiryResponse> getUnreadInquiriesForRealtor() {
        Long realtorId = SecurityUtil.getCurrentUserId();
        List<InquiryResponse> inquiries = inquiryDao.findUnreadByRealtorId(realtorId);
        inquiryDao.markAllAsReadByRealtorId(realtorId);
        return inquiries;
    }
}
