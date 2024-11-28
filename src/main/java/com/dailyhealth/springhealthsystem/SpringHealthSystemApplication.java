package com.dailyhealth.springhealthsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
@MapperScan("com.dailyhealth.springhealthsystem.mapper")
public class SpringHealthSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringHealthSystemApplication.class, args);
    }

}
