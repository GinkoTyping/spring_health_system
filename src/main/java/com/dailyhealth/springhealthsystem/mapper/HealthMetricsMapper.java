package com.dailyhealth.springhealthsystem.mapper;

import com.dailyhealth.springhealthsystem.model.HealthMetrics;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface HealthMetricsMapper {
    @Insert("INSERT INTO health_metrics(user_id, metric_type_id, metric_type_name, value) VALUES(#{userId}, #{metricTypeId}, #{metricTypeName}, #{value})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertHealthMetrics(HealthMetrics healthMetrics);

    @Select("SELECT * FROM health_metrics WHERE user_id = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "metricTypeId", column = "metric_type_id"),
            @Result(property = "metricTypeName", column = "metric_type_name"),
            @Result(property = "value", column = "value"),
            @Result(property = "recordedAt", column = "recorded_at")
    })
    List<HealthMetrics> getHealthMetricsByUserId(int userId);

    @Select("SELECT * FROM health_metrics")
    List<HealthMetrics> getHealthMetricsList();

    @Update("UPDATE health_metrics SET metric_type_id = #{metricTypeId}, metric_type_name = #{metricTypeName}, value = #{value} WHERE id = #{id}")
    int updateHealthMetricsById(HealthMetrics healthMetrics);

    @Delete("DELETE FROM health_metrics WHERE id = #{id}")
    int deleteHealthMetrics(int id);
}
