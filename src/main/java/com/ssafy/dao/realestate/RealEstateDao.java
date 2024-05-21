package com.ssafy.dao.realestate;

import com.ssafy.dto.realestate.RealEstate;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RealEstateDao {
    @Insert("INSERT INTO real_estate (name, registration_number, location) VALUES (#{name}, #{registrationNumber}, #{location})")
    @Options(useGeneratedKeys = true, keyProperty = "realEstateId")
    void save(RealEstate realEstate);

    @Select("SELECT r.* FROM real_estate r JOIN user_real_estate ur ON r.real_estate_id = ur.real_estate_id WHERE ur.user_id = #{userId}")
    @Results({
            @Result(property = "realEstateId", column = "real_estate_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "registrationNumber", column = "registration_number"),
            @Result(property = "location", column = "location")
    })
    RealEstate findByUserId(@Param("userId") Long userId);

    @Update("UPDATE real_estate SET name = #{name}, location = #{location} WHERE real_estate_id = #{realEstateId}")
    void update(@Param("realEstateId") Long realEstateId, @Param("name") String name, @Param("location") String location);
}
