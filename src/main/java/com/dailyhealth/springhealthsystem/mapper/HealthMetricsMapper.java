package com.dailyhealth.springhealthsystem.mapper;

import com.dailyhealth.springhealthsystem.model.HealthMetrics;
import com.dailyhealth.springhealthsystem.model.HealthMetricsType;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface HealthMetricsMapper {

    int insertHealthMetrics(HealthMetrics healthMetrics);


    List<HealthMetrics> getHealthMetricsByUserId(int userId);


    List<HealthMetrics> getHealthMetricsList();


    int updateHealthMetricsById(HealthMetrics healthMetrics);


    void updateHealthMetricsByType(HealthMetricsType healthMetricsType);


    int deleteHealthMetrics(int id);
}
