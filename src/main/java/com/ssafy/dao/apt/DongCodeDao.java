package com.ssafy.dao.apt;

import com.ssafy.dto.apt.DongCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

import java.util.List;

@Mapper
public interface DongCodeDao {

    @Select("SELECT * FROM dongcode WHERE dong_name LIKE CONCAT('%', #{query}, '%') OR sido_name LIKE CONCAT('%', #{query}, '%') OR gugun_name LIKE CONCAT('%', #{query}, '%')")
    @Results({
            @Result(column = "dong_code", property = "dongCode"),
            @Result(column = "sido_name", property = "sidoName"),
            @Result(column = "gugun_name", property = "gugunName"),
            @Result(column = "dong_name", property = "dongName")
    })
    List<DongCode> searchDongCodes(String query);
}
