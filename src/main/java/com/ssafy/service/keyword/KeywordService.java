package com.ssafy.service.keyword;

import com.ssafy.dao.keyword.KeywordDao;
import com.ssafy.dto.keyword.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class KeywordService {

    private final KeywordDao keywordDao;

    @Transactional
    public void setUserKeyword(Long userId, String keywordStr) {
        Integer keywordId = keywordDao.findKeywordIdByKeyword(keywordStr);
        if (keywordId == null) {
            Keyword keyword = new Keyword();
            keyword.setKeyword(keywordStr);
            keywordDao.insertKeyword(keyword);
            keywordId = keyword.getKeywordId();
        }

        if (keywordDao.countUserKeyword(userId, keywordId) == 0) {
            keywordDao.insertUserKeyword(userId, keywordId);
        }
    }

    @Transactional
    public void deleteUserKeyword(Long userId, String keyword) {
        Integer keywordId = keywordDao.findKeywordIdByKeyword(keyword);
        if (keywordId != null) {
            keywordDao.deleteUserKeyword(userId, keywordId);
        }
    }
}
