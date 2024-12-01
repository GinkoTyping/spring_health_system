package com.dailyhealth.springhealthsystem.mapper;

import com.dailyhealth.springhealthsystem.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    void insertUser(User user);

    User getUserById(int id);

    User getUserByUsername(String username);
}
