package com.dailyhealth.springhealthsystem.mapper;

import com.dailyhealth.springhealthsystem.model.HealthMetrics;
import com.dailyhealth.springhealthsystem.model.HealthMetricsType;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface HealthMetricsTypeMapper {
    @Insert("INSERT INTO health_metrics_types(name, description, unit) VALUES(#{name}, #{description}, #{unit})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertHealthMetricsType(HealthMetricsType healthMetricsType);

    @Select("SELECT * FROM health_metrics_types WHERE id = #{id}")
    HealthMetricsType getHealthMetricsTypeById(int id);

    @Select("SELECT * FROM health_metrics_types")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "unit", column = "unit"),
            @Result(property = "dataType", column = "data_type")
    })
    List<HealthMetricsType> getHealthMetricsTypesList();

    @Update("UPDATE health_metrics_types SET name = #{name}, description = #{description}, unit = #{unit} WHERE id = #{id}")
    int updateHealthMetricsTypeById(HealthMetricsType healthMetricsType);

    @Delete("DELETE FROM health_metrics_types WHERE id = #{id}")
    int deleteHealthMetricsType(int id);
}
