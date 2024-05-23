package com.ssafy.dao.keyword;

import com.ssafy.dto.keyword.KeywordNotification;
import com.ssafy.dto.keyword.KeywordNotificationResponse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface KeywordNotificationDao {

    @Insert("INSERT INTO keyword_notifications (user_id, keyword_id, apt_deal_id, notification_date, is_read) " +
            "VALUES (#{userId}, #{keywordId}, #{aptDealId}, #{notificationDate}, #{isRead})")
    @Options(useGeneratedKeys = true, keyProperty = "notificationId")
    void insertKeywordNotification(KeywordNotification notification);

    @Select("SELECT notification_id AS notificationId, user_id AS userId, keyword_id AS keywordId, apt_deal_id AS aptDealId, " +
            "notification_date AS notificationDate, is_read AS isRead " +
            "FROM keyword_notifications WHERE user_id = #{userId} AND is_read = false")
    List<KeywordNotification> findUnreadNotificationsByUserId(@Param("userId") Long userId);

    @Select("SELECT notification_id AS notificationId, user_id AS userId, keyword_id AS keywordId, apt_deal_id AS aptDealId, " +
            "notification_date AS notificationDate, is_read AS isRead " +
            "FROM keyword_notifications WHERE user_id = #{userId}")
    List<KeywordNotification> findAllNotificationsByUserId(@Param("userId") Long userId);

    @Select("SELECT kn.notification_date AS notificationDate, ad.name AS aptDealName " +
            "FROM keyword_notifications kn " +
            "JOIN apt_deal ad ON kn.apt_deal_id = ad.apt_deal_id " +
            "WHERE kn.user_id = #{userId} AND kn.is_read = false")
    List<KeywordNotificationResponse> findUnreadNotificationsWithDealNameByUserId(@Param("userId") Long userId);

    @Select("SELECT kn.notification_date AS notificationDate, ad.name AS aptDealName " +
            "FROM keyword_notifications kn " +
            "JOIN apt_deal ad ON kn.apt_deal_id = ad.apt_deal_id " +
            "WHERE kn.user_id = #{userId}")
    List<KeywordNotificationResponse> findAllNotificationsWithDealNameByUserId(@Param("userId") Long userId);

    @Update("UPDATE keyword_notifications kn " +
            "JOIN (SELECT notification_id FROM keyword_notifications WHERE user_id = #{userId} AND is_read = false) sub " +
            "ON kn.notification_id = sub.notification_id " +
            "SET kn.is_read = true")
    void markAllAsReadByUserId(@Param("userId") Long userId);
}
