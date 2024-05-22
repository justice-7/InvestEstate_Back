package com.ssafy.service.apt;


import com.ssafy.dao.apt.AptDealDao;
import com.ssafy.dao.keyword.KeywordDao;
import com.ssafy.dao.keyword.KeywordNotificationDao;
import com.ssafy.dto.apt.AptDeal;
import com.ssafy.dto.apt.AptInfo;
import com.ssafy.dto.keyword.Keyword;
import com.ssafy.dto.keyword.KeywordNotification;
import com.ssafy.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AptDealService {
    private final AptDealDao aptDealDao;
    private final KeywordDao keywordDao;
    private final KeywordNotificationDao keywordNotificationDao;

    @Transactional
    public void registerAptDeal(AptDeal aptDeal) {
        Long userId = SecurityUtil.getCurrentUserId();
        aptDeal.setUserId(userId);
        aptDealDao.insertAptDeal(aptDeal);
        // 이미지 URL 저장
        if (aptDeal.getImageUrls() != null && !aptDeal.getImageUrls().isEmpty()) {
            aptDealDao.insertAptImages(aptDeal.getAptDealId(), aptDeal.getImageUrls());
        }
        // aptDealId가 생성된 후 알림을 설정
        checkAndNotifyUsers(aptDeal);
    }


    private void checkAndNotifyUsers(AptDeal aptDeal) {
        List<Keyword> keywords = keywordDao.findAllKeywords();
        for (Keyword keyword : keywords) {
            if ((aptDeal.getDongName() != null && aptDeal.getDongName().contains(keyword.getKeyword())) ||
                    (aptDeal.getName() != null && aptDeal.getName().contains(keyword.getKeyword()))) {
                System.out.println(keyword.getKeywordId());
                List<Long> userIds = keywordDao.findUserIdsByKeywordId(keyword.getKeywordId());
                System.out.println(userIds);
                for (Long userId : userIds) {
                    KeywordNotification notification = new KeywordNotification();
                    notification.setUserId(userId);
                    notification.setKeywordId(keyword.getKeywordId());
                    notification.setAptDealId(aptDeal.getAptDealId());
                    keywordNotificationDao.insertKeywordNotification(notification);
                }
            }
        }
    }

    public void updateAptDeal(AptDeal aptDeal) {
        Long userId = SecurityUtil.getCurrentUserId();
        AptDeal existingAptDeal = aptDealDao.findById(aptDeal.getAptDealId());
        if (existingAptDeal != null && existingAptDeal.getUserId().equals(userId)) {
            aptDeal.setUserId(userId);
            aptDealDao.updateAptDeal(aptDeal);
        } else {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
    }

    public List<AptDeal> getAptDealsByRealtor() {
        Long userId = SecurityUtil.getCurrentUserId();
        return aptDealDao.findByUserId(userId);
    }

    public List<AptInfo> searchAptInfos(Integer priceMin, Integer priceMax, Integer area, String searchText) {
        return aptDealDao.searchAptInfos(priceMin, priceMax, area, searchText);
    }

    public List<AptDeal> findAptDealsByAptId(Integer aptId) {
        return aptDealDao.findAptDealsByAptId(aptId);
    }
}
