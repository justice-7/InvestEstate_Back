package com.ssafy.dao.keyword;

import com.ssafy.dto.keyword.Keyword;
import com.ssafy.dto.keyword.KeywordNotification;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface KeywordDao {

    @Insert("INSERT INTO keywords (keyword) VALUES (#{keyword.keyword})")
    @Options(useGeneratedKeys = true, keyProperty = "keyword.keywordId")
    void insertKeyword(@Param("keyword") Keyword keyword);

    @Insert("INSERT INTO user_keywords (user_id, keyword_id) VALUES (#{userId}, #{keywordId})")
    void insertUserKeyword(@Param("userId") Long userId, @Param("keywordId") Integer keywordId);

    @Select("SELECT keyword_id FROM keywords WHERE keyword = #{keyword}")
    Integer findKeywordIdByKeyword(@Param("keyword") String keyword);

    @Delete("DELETE FROM user_keywords WHERE user_id = #{userId} AND keyword_id = #{keywordId}")
    void deleteUserKeyword(@Param("userId") Long userId, @Param("keywordId") Integer keywordId);

    @Select("SELECT COUNT(*) FROM user_keywords WHERE user_id = #{userId} AND keyword_id = #{keywordId}")
    int countUserKeyword(@Param("userId") Long userId, @Param("keywordId") Integer keywordId);

    @Select("SELECT * FROM keywords")
    @Results({
            @Result(property = "keywordId", column = "keyword_id"),
            @Result(property = "keyword", column = "keyword")
    })
    List<Keyword> findAllKeywords();

    @Select("SELECT user_id FROM user_keywords WHERE keyword_id = #{keywordId}")
    List<Long> findUserIdsByKeywordId(@Param("keywordId") Integer keywordId);
}
