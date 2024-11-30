package com.dailyhealth.springhealthsystem.service;

import com.dailyhealth.springhealthsystem.mapper.HealthMetricsMapper;
import com.dailyhealth.springhealthsystem.model.HealthMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthMetricsService {
    private final HealthMetricsMapper healthMetricsMapper;

    @Autowired
    public HealthMetricsService(HealthMetricsMapper healthMetricsMapper) {
        this.healthMetricsMapper = healthMetricsMapper;
    }

    public List<HealthMetrics> getHealthMetricsList() {
        return healthMetricsMapper.getHealthMetricsList();
    }

    public List<HealthMetrics> getHealthMetricsByUserId(int userId) {
        return healthMetricsMapper.getHealthMetricsByUserId(userId);
    }

    public int insertHealthMetrics(int userId, int metricTypeId, Double value) {
        HealthMetrics healthMetrics = new HealthMetrics();
        healthMetrics.setUserId(userId);
        healthMetrics.setMetricTypeId(metricTypeId);
        healthMetrics.setValue(value);
        return healthMetricsMapper.insertHealthMetrics(healthMetrics);
    }
}
