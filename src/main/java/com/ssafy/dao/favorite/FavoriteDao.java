package com.ssafy.dao.favorite;

import com.ssafy.dto.favorite.Favorite;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FavoriteDao {

    @Insert("INSERT INTO favorites (user_id, apt_deal_id) VALUES (#{userId}, #{aptDealId})")
    void addFavorite(@Param("userId") Long userId, @Param("aptDealId") Long aptDealId);

    @Delete("DELETE FROM favorites WHERE user_id = #{userId} AND apt_deal_id = #{aptDealId}")
    void removeFavorite(@Param("userId") Long userId, @Param("aptDealId") Long aptDealId);

    @Select("SELECT f.user_id, f.apt_deal_id, a.* " +
            "FROM favorites f " +
            "JOIN apt_deal a ON f.apt_deal_id = a.apt_deal_id " +
            "WHERE f.user_id = #{userId}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "aptDealId", column = "apt_deal_id"),
            @Result(property = "aptDeal.aptDealId", column = "apt_deal_id"),
            @Result(property = "aptDeal.price", column = "price"),
            @Result(property = "aptDeal.builtYear", column = "built_year"),
            @Result(property = "aptDeal.year", column = "year"),
            @Result(property = "aptDeal.dongName", column = "dong_name"),
            @Result(property = "aptDeal.month", column = "month"),
            @Result(property = "aptDeal.day", column = "day"),
            @Result(property = "aptDeal.area", column = "area"),
            @Result(property = "aptDeal.jibun", column = "jibun"),
            @Result(property = "aptDeal.regionCode", column = "region_code"),
            @Result(property = "aptDeal.floor", column = "floor"),
            @Result(property = "aptDeal.name", column = "name"),
            @Result(property = "aptDeal.lat", column = "lat"),
            @Result(property = "aptDeal.lng", column = "lng"),
            @Result(property = "aptDeal.dongCode", column = "dong_code"),
            @Result(property = "aptDeal.aptId", column = "apt_id"),
            @Result(property = "aptDeal.userId", column = "user_id")
    })
    List<Favorite> findFavoritesByUserId(@Param("userId") Long userId);

    @Select("SELECT COUNT(*) FROM favorites WHERE user_id = #{userId} AND apt_deal_id = #{aptDealId}")
    int isFavorite(@Param("userId") Long userId, @Param("aptDealId") Long aptDealId);
}
