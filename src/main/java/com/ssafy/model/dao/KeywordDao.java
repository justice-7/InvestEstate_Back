package com.ssafy.model.dao;

import com.ssafy.model.dto.Keyword;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface KeywordDao {
    
    //@Select("SELECT * FROM keywords WHERE keyword = #{keyword}")
    Keyword findByKeyword(String keyword);

    //@Insert("INSERT INTO keywords (keyword) VALUES (#{keyword})")
    void insertKeyword(Keyword keyword);
}
