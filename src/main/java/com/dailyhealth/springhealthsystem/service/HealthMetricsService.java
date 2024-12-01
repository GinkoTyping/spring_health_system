package com.dailyhealth.springhealthsystem.service;

import com.dailyhealth.springhealthsystem.mapper.HealthMetricsMapper;
import com.dailyhealth.springhealthsystem.mapper.HealthMetricsTypeMapper;
import com.dailyhealth.springhealthsystem.model.HealthMetrics;
import com.dailyhealth.springhealthsystem.model.HealthMetricsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthMetricsService {
    private final HealthMetricsMapper healthMetricsMapper;
    private final HealthMetricsTypeMapper healthMetricsTypeMapper;

    @Autowired
    public HealthMetricsService(HealthMetricsMapper healthMetricsMapper, HealthMetricsTypeMapper healthMetricsTypeMapper) {
        this.healthMetricsMapper = healthMetricsMapper;
        this.healthMetricsTypeMapper = healthMetricsTypeMapper;
    }

    public List<HealthMetrics> getHealthMetricsList() {
        return healthMetricsMapper.getHealthMetricsList();
    }

    public List<HealthMetrics> getHealthMetricsByUserId(int userId) {
        return healthMetricsMapper.getHealthMetricsByUserId(userId);
    }

    public int insertHealthMetrics(int userId, int metricTypeId, Double value) {
        HealthMetricsType metricsType = healthMetricsTypeMapper.getHealthMetricsTypeById(metricTypeId);

        HealthMetrics healthMetrics = new HealthMetrics();
        healthMetrics.setUserId(userId);
        healthMetrics.setMetricTypeId(metricTypeId);
        healthMetrics.setValue(value);
        healthMetrics.setMetricTypeName(metricsType.getName());

        return healthMetricsMapper.insertHealthMetrics(healthMetrics);
    }

    public int updateHealthMetrics(int id, int metricTypeId, Double value) {
        HealthMetricsType metricsType = healthMetricsTypeMapper.getHealthMetricsTypeById(metricTypeId);
        if (metricsType == null) {
            return 0;
        }
        HealthMetrics healthMetrics = new HealthMetrics();
        healthMetrics.setId(id);
        healthMetrics.setMetricTypeId(metricTypeId);
        healthMetrics.setValue(value);
        healthMetrics.setMetricTypeName(metricsType.getName());

        return healthMetricsMapper.updateHealthMetricsById(healthMetrics);
    }

    public int deleteHealthMetrics(int id) {
        return healthMetricsMapper.deleteHealthMetrics(id);
    }

    public HealthMetrics getHealthMetricsById(int id) {
        return healthMetricsMapper.getHealthMetricsById(id);
    }
}
