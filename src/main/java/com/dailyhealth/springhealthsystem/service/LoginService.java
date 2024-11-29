package com.dailyhealth.springhealthsystem.service;

import com.dailyhealth.springhealthsystem.mapper.UserMapper;
import com.dailyhealth.springhealthsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserMapper userMapper;
    @Autowired
    public LoginService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User login(String username, String password) {
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            return null;
        }
        if (user.checkPassword(password)) {
            return user;
        }
        return null;
    }
}
