package com.ssafy.dao.apt;

import com.ssafy.dto.apt.AptInfo;
import com.ssafy.dto.apt.JoinResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AptInfoDao {

    @Select("SELECT ai.apt_id AS aptId, dc.sido_name AS sidoName, dc.gugun_name AS gugunName, dc.dong_name AS dongName, ai.name AS name, ai.jibun AS jibun, ai.built_year AS builtYear " +
            "FROM apt_info ai " +
            "JOIN dongcode dc ON ai.dong_code = dc.dong_code " +
            "WHERE ai.name LIKE CONCAT('%', #{query}, '%') " +
            "OR dc.dong_name LIKE CONCAT('%', #{query}, '%') " +
            "OR dc.sido_name LIKE CONCAT('%', #{query}, '%') " +
            "OR dc.gugun_name LIKE CONCAT('%', #{query}, '%')")
    List<JoinResult> searchAptInfosWithDong(String query);


    @Select("SELECT dc.sido_name AS sidoName, dc.gugun_name AS gugunName, dc.dong_name AS dongName, ai.name AS name, ai.jibun AS jibun, ai.built_year AS builtYear, ai.lat AS lat, ai.lng AS lng " +
            "FROM apt_info ai " +
            "JOIN dongcode dc ON ai.dong_code = dc.dong_code " +
            "WHERE ai.apt_id = #{aptId}")
    JoinResult findAptInfoById(Long aptId);

    @Select("SELECT * FROM apt_info WHERE apt_id = #{aptId}")
    @Results({
            @Result(property = "aptId", column = "apt_id"),
            @Result(property = "builtYear", column = "built_year"),
            @Result(property = "dongCode", column = "dong_code"),
            @Result(property = "dongName", column = "dong_name"),
            @Result(property = "jibun", column = "jibun"),
            @Result(property = "name", column = "name"),
            @Result(property = "lat", column = "lat"),
            @Result(property = "lng", column = "lng")
    })
    AptInfo findById(Long aptId);
}
