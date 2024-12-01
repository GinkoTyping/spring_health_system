package com.dailyhealth.springhealthsystem.service;

import com.dailyhealth.springhealthsystem.mapper.HealthMetricsMapper;
import com.dailyhealth.springhealthsystem.mapper.HealthMetricsTypeMapper;
import com.dailyhealth.springhealthsystem.model.HealthMetrics;
import com.dailyhealth.springhealthsystem.model.HealthMetricsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/health-metrics-type")
public class HealthMetricsTypeService {
    private final HealthMetricsTypeMapper healthMetricsTypeMapper;
    private final HealthMetricsMapper healthMetricsMapper;


    @Autowired
    public HealthMetricsTypeService(HealthMetricsTypeMapper healthMetricsTypeMapper, HealthMetricsMapper healthMetricsMapper) {
        this.healthMetricsTypeMapper = healthMetricsTypeMapper;
        this.healthMetricsMapper = healthMetricsMapper;
    }

    public List<HealthMetricsType> getHealthMetricsTypesList() {
        return healthMetricsTypeMapper.getHealthMetricsTypesList();
    }

    public HealthMetricsType getHealthMetricsTypeById(int id) {
        return healthMetricsTypeMapper.getHealthMetricsTypeById(id);
    }

    @Transactional
    public int updateHealthMetricsType(int id, String name, String description, String unit) {
        HealthMetricsType healthMetricsType = new HealthMetricsType();
        healthMetricsType.setName(name);
        healthMetricsType.setDescription(description);
        healthMetricsType.setUnit(unit);
        healthMetricsType.setId(id);

        int output = healthMetricsTypeMapper.updateHealthMetricsTypeById(healthMetricsType);

        // health_metrics 上展示了 metrics_type_name ，需要同步更新保持UI界面的数据一致性；
        if (output > 0) {
            healthMetricsMapper.updateHealthMetricsByType(healthMetricsType);
        }
        return output;
    }

    public int deleteHealthMetricsType(int id) {
        List<HealthMetrics> healthMetricsList = healthMetricsMapper.getHealthMetricsByTypeId(id);
        if (healthMetricsList.isEmpty()) {
            return healthMetricsTypeMapper.deleteHealthMetricsType(id);
        }
        return 0;
    }

    public int insertHealthMetricsType(String name, String description, String unit) {
        HealthMetricsType healthMetricsType = new HealthMetricsType();
        healthMetricsType.setName(name);
        healthMetricsType.setDescription(description);
        healthMetricsType.setUnit(unit);

        return healthMetricsTypeMapper.insertHealthMetricsType(healthMetricsType);
    }
}
