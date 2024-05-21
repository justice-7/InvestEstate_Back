package com.ssafy.service;

import com.ssafy.model.dao.KeywordDao;
import com.ssafy.model.dto.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeywordService {

    @Autowired
    private KeywordDao dao;

    public void addKeyword(String keyword) {
        Keyword newKeyword = new Keyword();
        newKeyword.setKeyword(keyword);
        dao.insertKeyword(newKeyword);
    }

    public Keyword findByKeyword(String keyword) {
        return dao.findByKeyword(keyword);
    }
}
