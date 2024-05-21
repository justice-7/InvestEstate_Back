package com.ssafy.dao.user;

import com.ssafy.dto.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RealtorDao {

    @Select("SELECT * FROM users WHERE email = #{email}")
    User findByEmail(@Param("email") String email);

    @Update("UPDATE users SET password = #{password} WHERE user_id = #{id}")
    void updatePassword(@Param("id") Long id, @Param("password") String password);
}