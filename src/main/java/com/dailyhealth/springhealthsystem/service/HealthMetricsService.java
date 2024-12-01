package com.dailyhealth.springhealthsystem.service;

import com.dailyhealth.springhealthsystem.mapper.HealthMetricsMapper;
import com.dailyhealth.springhealthsystem.mapper.HealthMetricsTypeMapper;
import com.dailyhealth.springhealthsystem.model.HealthMetrics;
import com.dailyhealth.springhealthsystem.model.HealthMetricsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthMetricsService {
    private final HealthMetricsMapper healthMetricsMapper;
    private final HealthMetricsTypeMapper healthMetricsTypeMapper;
    private final HealthMetricsTypeService healthMetricsTypeService;

    @Autowired
    public HealthMetricsService(HealthMetricsMapper healthMetricsMapper, HealthMetricsTypeMapper healthMetricsTypeMapper, HealthMetricsTypeService healthMetricsTypeService) {
        this.healthMetricsMapper = healthMetricsMapper;
        this.healthMetricsTypeMapper = healthMetricsTypeMapper;
        this.healthMetricsTypeService = healthMetricsTypeService;
    }

    public List<HealthMetrics> getHealthMetricsList() {
        return healthMetricsMapper.getHealthMetricsList();
    }

    public List<HealthMetrics> getHealthMetricsByUserId(int userId) {
        return healthMetricsMapper.getHealthMetricsByUserId(userId);
    }

    public int insertHealthMetrics(int userId, int metricTypeId, Double value) {
        HealthMetricsType metricsType = validateMetricsType(metricTypeId);
        if (metricsType == null) {
            return 0;
        }

        HealthMetrics healthMetrics = new HealthMetrics();
        healthMetrics.setUserId(userId);
        healthMetrics.setMetricTypeId(metricTypeId);
        healthMetrics.setValue(value);
        healthMetrics.setMetricTypeName(metricsType.getName());

        return healthMetricsMapper.insertHealthMetrics(healthMetrics);
    }

    public int updateHealthMetrics(int id, int metricTypeId, Double value) {
        HealthMetricsType metricsType = validateMetricsType(metricTypeId, id);
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

    public int deleteHealthMetrics(int id, int typeId) {
        if (healthMetricsTypeService.isUniqueByMetricsTypeId(typeId)) {
            return 0;
        }
        return healthMetricsMapper.deleteHealthMetrics(id);
    }

    public HealthMetrics getHealthMetricsById(int id) {
        return healthMetricsMapper.getHealthMetricsById(id);
    }

    /**
     * 添加健康数据时，验证并返回指定 健康数据类型ID 的 健康数据类型。
     *
     * @param metricTypeId 健康数据类型的ID
     * @return 如果找到匹配的健康指标类型，则返回该健康指标类型；否则返回null
     */
    public HealthMetricsType validateMetricsType(int metricTypeId) {
        HealthMetricsType metricsType = healthMetricsTypeMapper.getHealthMetricsTypeById(metricTypeId);

        // 不存在 typeId
        if (metricsType == null) {
            return null;
        }

        if (metricsType.getIsUnique() > 0) {
            List<HealthMetrics> healthMetricsList = healthMetricsMapper.getHealthMetricsByTypeId(metricTypeId);
            return healthMetricsList.isEmpty() ? metricsType : null;
        }
        return metricsType;
    }

    /**
     * 更新健康数据时，验证并返回指定 健康数据类型ID 的 健康数据类型。
     *
     * @param metricTypeId 健康数据类型的ID
     * @param metricId     健康数据的ID
     * @return 如果找到匹配的健康指标类型，则返回该健康指标类型；否则返回null
     */
    public HealthMetricsType validateMetricsType(int metricTypeId, int metricId) {
        HealthMetricsType metricsType = healthMetricsTypeMapper.getHealthMetricsTypeById(metricTypeId);

        // 不存在 typeId
        if (metricsType == null) {
            return null;
        }

        // 关联了unique typeId健康数据 只能存在一条
        if (metricsType.getIsUnique() > 0) {
            List<HealthMetrics> healthMetricsList = healthMetricsMapper.getHealthMetricsByTypeId(metricTypeId);
            Optional<HealthMetrics> found = healthMetricsList.stream().filter(item -> item.getId() == metricId).findFirst();

            boolean isAnotherMetrics = found.isEmpty();
            if (isAnotherMetrics) {
                return null;
            }
        }

        return metricsType;
    }
}
