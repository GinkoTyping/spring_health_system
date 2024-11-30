package com.dailyhealth.springhealthsystem.model;


import java.sql.Timestamp;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private Timestamp createdAt;

    public boolean checkPassword(String input) {
        return this.password.equals(input);
    }
    public String getUsername() {
        return username;
    }
}
