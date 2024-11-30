package com.dailyhealth.springhealthsystem.service;

import com.dailyhealth.springhealthsystem.mapper.HealthMetricsTypeMapper;
import com.dailyhealth.springhealthsystem.model.HealthMetricsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/health-metrics-type")
public class HealthMetricsTypeService {
    private final HealthMetricsTypeMapper healthMetricsTypeMapper;

    @Autowired
    public HealthMetricsTypeService(HealthMetricsTypeMapper healthMetricsTypeMapper) {
        this.healthMetricsTypeMapper = healthMetricsTypeMapper;
    }

    public List<HealthMetricsType> getHealthMetricsTypesList() {
        return healthMetricsTypeMapper.getHealthMetricsTypesList();
    }

    public HealthMetricsType getHealthMetricsTypeById(int id) {
        return healthMetricsTypeMapper.getHealthMetricsTypeById(id);
    }

    public int updateHealthMetricsType(int id, String name, String description, String unit) {
        HealthMetricsType healthMetricsType = new HealthMetricsType();
        healthMetricsType.setName(name);
        healthMetricsType.setDescription(description);
        healthMetricsType.setUnit(unit);
        healthMetricsType.setId(id);

        return healthMetricsTypeMapper.updateHealthMetricsTypeById(healthMetricsType);
    }
}
