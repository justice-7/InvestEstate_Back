package com.ssafy.dao.apt;

import com.ssafy.dto.apt.AptDeal;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AptDealDao {
    @Insert("INSERT INTO apt_deal (price, built_year, year, dong_name, month, day, area, jibun, region_code, floor, name, lat, lng, dong_code, apt_id, user_id) " +
            "VALUES (#{price}, #{builtYear}, #{year}, #{dongName}, #{month}, #{day}, #{area}, #{jibun}, #{regionCode}, #{floor}, #{name}, #{lat}, #{lng}, #{dongCode}, #{aptId}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "aptDealId")
    void insertAptDeal(AptDeal aptDeal);

    @Insert("<script>" +
            "INSERT INTO apt_images (apt_deal_id, image_url) VALUES " +
            "<foreach collection='imageUrls' item='imageUrl' separator=','>" +
            "(#{aptDealId}, #{imageUrl})" +
            "</foreach>" +
            "</script>")
    void insertAptImages(@Param("aptDealId") Long aptDealId, @Param("imageUrls") List<String> imageUrls);

    @Update("UPDATE apt_deal SET price = #{price}, built_year = #{builtYear}, year = #{year}, dong_name = #{dongName}, " +
            "month = #{month}, day = #{day}, area = #{area}, jibun = #{jibun}, region_code = #{regionCode}, " +
            "floor = #{floor}, name = #{name}, lat = #{lat}, lng = #{lng}, dong_code = #{dongCode}, apt_id = #{aptId} " +
            "WHERE apt_deal_id = #{aptDealId} AND user_id = #{userId}")
    void updateAptDeal(AptDeal aptDeal);

    @Select("SELECT * FROM apt_deal WHERE apt_deal_id = #{aptDealId}")
    @Results({
            @Result(property = "aptDealId", column = "apt_deal_id"),
            @Result(property = "price", column = "price"),
            @Result(property = "builtYear", column = "built_year"),
            @Result(property = "year", column = "year"),
            @Result(property = "dongName", column = "dong_name"),
            @Result(property = "month", column = "month"),
            @Result(property = "day", column = "day"),
            @Result(property = "area", column = "area"),
            @Result(property = "jibun", column = "jibun"),
            @Result(property = "regionCode", column = "region_code"),
            @Result(property = "floor", column = "floor"),
            @Result(property = "name", column = "name"),
            @Result(property = "lat", column = "lat"),
            @Result(property = "lng", column = "lng"),
            @Result(property = "dongCode", column = "dong_code"),
            @Result(property = "aptId", column = "apt_id"),
            @Result(property = "userId", column = "user_id")
    })
    AptDeal findById(Long aptDealId);

    @Select("SELECT ad.* " +
            "FROM apt_deal ad " +
            "WHERE ad.user_id = #{userId}")
    @Results({
            @Result(property = "aptDealId", column = "apt_deal_id"),
            @Result(property = "price", column = "price"),
            @Result(property = "builtYear", column = "built_year"),
            @Result(property = "year", column = "year"),
            @Result(property = "dongName", column = "dong_name"),
            @Result(property = "month", column = "month"),
            @Result(property = "day", column = "day"),
            @Result(property = "area", column = "area"),
            @Result(property = "jibun", column = "jibun"),
            @Result(property = "regionCode", column = "region_code"),
            @Result(property = "floor", column = "floor"),
            @Result(property = "name", column = "name"),
            @Result(property = "lat", column = "lat"),
            @Result(property = "lng", column = "lng"),
            @Result(property = "dongCode", column = "dong_code"),
            @Result(property = "aptId", column = "apt_id"),
            @Result(property = "userId", column = "user_id")
    })
    List<AptDeal> findByUserId(Long userId);

    @Select("<script>" +
            "SELECT * FROM apt_deal WHERE 1=1" +
            "<if test='priceMin != null'> AND price &gt;= #{priceMin}</if>" +
            "<if test='priceMax != null'> AND price &lt;= #{priceMax}</if>" +
            "<if test='area != null'>" +
            " AND area &gt;= #{area} AND area &lt;= #{area + 10}" +
            "</if>" +
            "<if test='searchText != null'>" +
            " AND (dong_name LIKE CONCAT('%', #{searchText}, '%')" +
            " OR name LIKE CONCAT('%', #{searchText}, '%')" +
            " OR jibun LIKE CONCAT('%', #{searchText}, '%'))" +
            "</if>" +
            "</script>")
    @Results({
            @Result(column = "apt_deal_id", property = "aptDealId"),
            @Result(column = "price", property = "price"),
            @Result(column = "built_year", property = "builtYear"),
            @Result(column = "year", property = "year"),
            @Result(column = "dong_name", property = "dongName"),
            @Result(column = "month", property = "month"),
            @Result(column = "day", property = "day"),
            @Result(column = "area", property = "area"),
            @Result(column = "jibun", property = "jibun"),
            @Result(column = "region_code", property = "regionCode"),
            @Result(column = "floor", property = "floor"),
            @Result(column = "name", property = "name"),
            @Result(column = "lat", property = "lat"),
            @Result(column = "lng", property = "lng"),
            @Result(column = "dong_code", property = "dongCode"),
            @Result(column = "apt_id", property = "aptId"),
            @Result(column = "user_id", property = "userId")
    })
    List<AptDeal> searchAptDeals(@Param("priceMin") Integer priceMin,
                                 @Param("priceMax") Integer priceMax,
                                 @Param("area") Integer area,
                                 @Param("searchText") String searchText);
}
