package com.dailyhealth.springhealthsystem.mapper;

import com.dailyhealth.springhealthsystem.model.HealthMetricsType;

import java.util.List;

public interface HealthMetricsTypeMapper {
    int insertHealthMetricsType(HealthMetricsType healthMetricsType);

    HealthMetricsType getHealthMetricsTypeById(int id);

    List<HealthMetricsType> getHealthMetricsTypesList();

    int updateHealthMetricsTypeById(HealthMetricsType healthMetricsType);

    int deleteHealthMetricsType(int id);
}
