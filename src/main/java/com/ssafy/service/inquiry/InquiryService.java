package com.ssafy.service.inquiry;

import com.ssafy.dao.inquiry.InquiryDao;
import com.ssafy.dto.inquiry.InquiryRequest;
import com.ssafy.dto.inquiry.InquiryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryDao inquiryDao;
    private final JavaMailSender mailSender;

    @Transactional
    public void createInquiry(InquiryRequest inquiryRequest) {
        inquiryDao.save(inquiryRequest);
        sendEmailToRealtor(inquiryRequest);
    }

    public List<InquiryResponse> getInquiriesForUser(Long userId) {
        return inquiryDao.findByUserId(userId);
    }

    public List<InquiryResponse> getInquiriesForRealtor() {
        return inquiryDao.findByRealtorId();
    }

    public List<InquiryResponse> getUnreadInquiriesForRealtor() {
        List<InquiryResponse> inquiries = inquiryDao.findUnreadByRealtorId();
        inquiryDao.markAllAsReadByRealtorId();
        return inquiries;
    }

    private void sendEmailToRealtor(InquiryRequest inquiryRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("ojs835@naver.com");
        message.setSubject("새로운 부동산 문의가 등록되었습니다.");
        message.setText("문의 내용: " + inquiryRequest.getContent() + "\n매물 ID: " + inquiryRequest.getAptId());
        mailSender.send(message);
    }
}
