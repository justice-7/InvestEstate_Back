package com.ssafy.dao.user;

import com.ssafy.dto.auth.RealtorSignUpRequest;
import com.ssafy.dto.auth.SignUpRequest;
import com.ssafy.dto.user.User;
import com.ssafy.dto.user.UserUpdateRequest;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    @Select("SELECT * FROM users WHERE email = #{email}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "role", column = "role"),
            @Result(property = "phonenumber", column = "phonenumber")
    })
    User findByEmail(String email);

    @Insert("INSERT INTO users (name, email, password, role, phonenumber) VALUES (#{name}, #{email}, #{password}, #{role}, #{phonenumber})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void save(SignUpRequest signUpRequest);

    @Insert("INSERT INTO users (name, email, password, role, phonenumber) VALUES (#{name}, #{email}, #{password}, #{role}, #{phonenumber})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void saveRealtor(RealtorSignUpRequest signUpRequest);

    @Update("UPDATE users SET name = #{userUpdateRequest.name}, phonenumber = #{userUpdateRequest.phoneNumber} WHERE user_id = #{userId}")
    void updateUser(@Param("userId") Long userId, @Param("userUpdateRequest") UserUpdateRequest userUpdateRequest);

    @Select("SELECT * FROM users WHERE user_id = #{userId}")
    User findById(@Param("userId") Long userId);

    @Update("UPDATE users SET name = #{name}, phonenumber = #{phoneNumber} WHERE user_id = #{userId}")
    void update(@Param("userId") Long userId, @Param("name") String name, @Param("phoneNumber") String phoneNumber);
}
