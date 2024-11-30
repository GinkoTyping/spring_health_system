package com.dailyhealth.springhealthsystem.model;

import java.sql.Date;
import java.sql.Timestamp;

public class HealthMetrics {
    private int id;
    private int userId;
    private int metricTypeId;
    private Double value;
    private Date recordedAt;

    private String metricTypeName;

    public String getMetricTypeName() {
        return metricTypeName;
    }

    public void setMetricTypeName(String metricTypeName) {
        this.metricTypeName = metricTypeName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMetricTypeId() {
        return metricTypeId;
    }

    public void setMetricTypeId(int metricTypeId) {
        this.metricTypeId = metricTypeId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(Date recordedAt) {
        this.recordedAt = recordedAt;
    }
}
