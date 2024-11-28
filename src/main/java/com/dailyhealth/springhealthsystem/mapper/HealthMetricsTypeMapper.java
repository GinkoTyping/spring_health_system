package com.dailyhealth.springhealthsystem.mapper;

import com.dailyhealth.springhealthsystem.model.HealthMetricsType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
public interface HealthMetricsTypeMapper {
    @Insert("INSERT INTO health_metrics_types(name, description, unit, data_type) VALUES(#{name}, #{description}, #{unit}, #{dataType})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertHealthMetricsType(HealthMetricsType healthMetricsType);

    @Select("SELECT * FROM health_metrics_types WHERE id = #{id}")
    HealthMetricsType getHealthMetricsTypeById(int id);
}
