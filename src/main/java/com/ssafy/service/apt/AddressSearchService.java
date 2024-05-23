package com.ssafy.service.apt;

import com.ssafy.dao.apt.AptInfoDao;
import com.ssafy.dao.apt.DongCodeDao;
import com.ssafy.dto.apt.JoinResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressSearchService {

    private final DongCodeDao dongCodeDao;
    private final AptInfoDao aptInfoDao;

    public List<JoinResult> searchAddress(String query) {
        return aptInfoDao.searchAptInfosWithDong(query);
    }

    public JoinResult getAddressDetails(Long aptId) {
        return aptInfoDao.findAptInfoById(aptId);
    }
}
