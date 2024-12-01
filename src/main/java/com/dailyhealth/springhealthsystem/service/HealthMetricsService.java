package com.dailyhealth.springhealthsystem.service;

import com.dailyhealth.springhealthsystem.mapper.HealthMetricsMapper;
import com.dailyhealth.springhealthsystem.mapper.HealthMetricsTypeMapper;
import com.dailyhealth.springhealthsystem.mapper.UserMapper;
import com.dailyhealth.springhealthsystem.model.HealthMetrics;
import com.dailyhealth.springhealthsystem.model.HealthMetricsType;
import com.dailyhealth.springhealthsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthMetricsService {
    private final HealthMetricsMapper healthMetricsMapper;
    private final HealthMetricsTypeMapper healthMetricsTypeMapper;
    private final HealthMetricsTypeService healthMetricsTypeService;
    private final UserMapper userMapper;

    @Autowired
    public HealthMetricsService(HealthMetricsMapper healthMetricsMapper, HealthMetricsTypeMapper healthMetricsTypeMapper, HealthMetricsTypeService healthMetricsTypeService, UserMapper userMapper) {
        this.healthMetricsMapper = healthMetricsMapper;
        this.healthMetricsTypeMapper = healthMetricsTypeMapper;
        this.healthMetricsTypeService = healthMetricsTypeService;
        this.userMapper = userMapper;
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

    public List<HealthMetrics> getHealthMetricsByUserName(String username) {
        User user = userMapper.getUserByUsername(username);
        return healthMetricsMapper.getHealthMetricsByUserId(user.getId());
    }

    public String getAdviceBMI() {
        HealthMetrics height = healthMetricsMapper.getHealthMetricsByTypeId(1).get(0);
        HealthMetrics weight = healthMetricsMapper.getHealthMetricsByTypeId(2).get(0);
        if (height != null && weight != null) {
            double heightCm = height.getValue();
            double weightKg = weight.getValue();

            double bmi = weightKg / (Math.pow(heightCm / 100, 2));
            bmi = Math.round(bmi * 10.0) / 10.0;

            String advice;
            if (bmi < 18.5) {
                advice = "您的身材偏瘦，请多吃饭！";
            } else if (bmi >= 18.5 && bmi < 24.9) {
                advice = "您的身材正常，请继续保持！";
            } else if (bmi >= 25 && bmi < 29.9) {
                advice = "您的身材偏胖，请注意饮食或加强运动！";
            } else {
                advice = "您的身材肥胖，请不要暴饮暴食的同时，进行适度的锻炼！";
            }

            return "你的BMI值为: " + bmi + "，建议: " + advice;
        } else {
            return "无法获取身高或体重数据，无法计算BMI。";
        }
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

    public List<HealthMetrics> getHealthMetricsByTypeId(int id) {
        return healthMetricsMapper.getHealthMetricsByTypeId(id);
    }
}
