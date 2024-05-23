package com.ssafy.dao.favorite;

import com.ssafy.dto.favorite.Favorite;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FavoriteDao {

    @Insert("INSERT INTO favorites (user_id, apt_id) VALUES (#{userId}, #{aptId})")
    void addFavorite(@Param("userId") Long userId, @Param("aptId") Long aptId);

    @Delete("DELETE FROM favorites WHERE user_id = #{userId} AND apt_id = #{aptId}")
    void removeFavorite(@Param("userId") Long userId, @Param("aptId") Long aptId);

    @Select("SELECT f.*, a.* FROM favorites f JOIN apt_info a ON f.apt_id = a.apt_id WHERE f.user_id = #{userId}")
    @Results({
            @Result(property = "favoriteId", column = "favorite_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "aptId", column = "apt_id"),
            @Result(property = "aptInfo.aptId", column = "apt_id"),
            @Result(property = "aptInfo.name", column = "name"),
            @Result(property = "aptInfo.dongName", column = "dong_name"),
            @Result(property = "aptInfo.jibun", column = "jibun"),
            @Result(property = "aptInfo.lat", column = "lat"),
            @Result(property = "aptInfo.lng", column = "lng"),
            @Result(property = "aptInfo.builtYear", column = "built_year"),
            @Result(property = "aptInfo.dongCode", column = "dong_code")
    })
    List<Favorite> findFavoritesByUserId(@Param("userId") Long userId);

    @Select("SELECT COUNT(*) FROM favorites WHERE user_id = #{userId} AND apt_id = #{aptId}")
    int isFavorite(@Param("userId") Long userId, @Param("aptId") Long aptId);
}
