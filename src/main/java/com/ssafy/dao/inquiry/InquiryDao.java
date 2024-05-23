package com.ssafy.dao.inquiry;

import com.ssafy.dto.inquiry.InquiryRequest;
import com.ssafy.dto.inquiry.InquiryResponse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InquiryDao {

    @Insert("INSERT INTO inquiries (user_id, apt_deal_id, content, status, is_read) VALUES (1, #{inquiryRequest.aptId}, #{inquiryRequest.content}, 'pending', false)")
    @Options(useGeneratedKeys = true, keyProperty = "inquiryRequest.inquiryId")
    void save(@Param("inquiryRequest") InquiryRequest inquiryRequest);

    @Select("SELECT * FROM inquiries WHERE user_id = #{userId}")
    @Results({
            @Result(property = "inquiryId", column = "inquiry_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "aptId", column = "apt_deal_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "status", column = "status"),
            @Result(property = "isRead", column = "is_read")
    })
    List<InquiryResponse> findByUserId(Long userId);

    @Select("SELECT * FROM inquiries WHERE user_id = 1")
    @Results({
            @Result(property = "inquiryId", column = "inquiry_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "aptId", column = "apt_deal_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "status", column = "status"),
            @Result(property = "isRead", column = "is_read")
    })
    List<InquiryResponse> findByRealtorId();

    @Select("SELECT * FROM inquiries WHERE user_id = 1 AND is_read = false")
    @Results({
            @Result(property = "inquiryId", column = "inquiry_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "aptId", column = "apt_deal_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "status", column = "status"),
            @Result(property = "isRead", column = "is_read")
    })
    List<InquiryResponse> findUnreadByRealtorId();

    @Update("UPDATE inquiries SET is_read = true WHERE user_id = 1 AND is_read = false")
    void markAllAsReadByRealtorId();
}
