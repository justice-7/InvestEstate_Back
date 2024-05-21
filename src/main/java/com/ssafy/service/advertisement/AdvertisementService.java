package com.ssafy.service.advertisement;

import com.ssafy.dao.advertisement.AdvertisementDao;
import com.ssafy.dto.advertisement.AdvertisementRequest;
import com.ssafy.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdvertisementService {
    private final AdvertisementDao advertisementDao;

    @Transactional
    public void createAdvertisement(AdvertisementRequest advertisementRequest) {
        // 관리자 권한 체크
        if (!SecurityUtil.hasRole("ADMIN")) {
            throw new SecurityException("관리자 권한이 필요합니다.");
        }
        advertisementDao.saveAdvertisement(advertisementRequest);
    }
}
