package com.ssafy.dao.realestate;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRealEstateDao {

    @Insert("INSERT INTO user_real_estate (user_id, real_estate_id) VALUES (#{userId}, #{realEstateId})")
    void save(@Param("userId") Long userId, @Param("realEstateId") Long realEstateId);
}
