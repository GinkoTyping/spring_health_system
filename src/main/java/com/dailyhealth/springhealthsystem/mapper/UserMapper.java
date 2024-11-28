package com.dailyhealth.springhealthsystem.mapper;

import com.dailyhealth.springhealthsystem.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Insert("INSERT INTO users(username, email, password) VALUES(#{username}, #{email}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    @Select("SELECT * FROM users WHERE id = #{id}")
    User getUserById(int id);
}
