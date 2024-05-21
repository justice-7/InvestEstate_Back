package com.ssafy.dao.inquiry;

import com.ssafy.dto.inquiry.InquiryRequest;
import com.ssafy.dto.inquiry.InquiryResponse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InquiryDao {

    @Insert("INSERT INTO inquiries (user_id, apt_deal_id, message, status, is_read) VALUES (#{userId}, #{inquiryRequest.aptDealId}, #{inquiryRequest.message}, 'pending', false)")
    @Options(useGeneratedKeys = true, keyProperty = "inquiryRequest.inquiryId")
    void save(@Param("userId") Long userId, @Param("inquiryRequest") InquiryRequest inquiryRequest);

    @Select("SELECT * FROM inquiries WHERE user_id = #{userId}")
    @Results({
            @Result(property = "inquiryId", column = "inquiry_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "aptDealId", column = "apt_deal_id"),
            @Result(property = "message", column = "message"),
            @Result(property = "status", column = "status"),
            @Result(property = "isRead", column = "is_read")
    })
    List<InquiryResponse> findByUserId(Long userId);

    @Select("SELECT * FROM inquiries WHERE user_id = #{realtorId}")
    @Results({
            @Result(property = "inquiryId", column = "inquiry_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "aptDealId", column = "apt_deal_id"),
            @Result(property = "message", column = "message"),
            @Result(property = "status", column = "status"),
            @Result(property = "isRead", column = "is_read")
    })
    List<InquiryResponse> findByRealtorId(Long realtorId);

    @Select("SELECT * FROM inquiries WHERE user_id = #{realtorId} AND is_read = false")
    @Results({
            @Result(property = "inquiryId", column = "inquiry_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "aptDealId", column = "apt_deal_id"),
            @Result(property = "message", column = "message"),
            @Result(property = "status", column = "status"),
            @Result(property = "isRead", column = "is_read")
    })
    List<InquiryResponse> findUnreadByRealtorId(Long realtorId);

    @Update("UPDATE inquiries SET is_read = true WHERE apt_deal_id IN (SELECT apt_deal_id FROM apt_deal WHERE user_id = #{realtorId}) AND is_read = false")
    void markAllAsReadByRealtorId(Long realtorId);
}
