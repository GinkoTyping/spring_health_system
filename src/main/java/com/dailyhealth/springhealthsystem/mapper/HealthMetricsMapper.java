package com.dailyhealth.springhealthsystem.mapper;

import com.dailyhealth.springhealthsystem.model.HealthMetrics;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
public interface HealthMetricsMapper {
    @Insert("INSERT INTO health_metrics(user_id, metric_type_id, value, recorded_at) VALUES(#{userId}, #{metricTypeId}, #{value}, #{recordedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertHealthMetrics(HealthMetrics healthMetrics);

    @Select("SELECT * FROM health_metrics WHERE id = #{id}")
    HealthMetrics getHealthMetricsById(int id);
}
