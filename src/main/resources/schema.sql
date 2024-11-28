CREATE
DATABASE IF NOT EXISTS health_monitor;

USE
health_monitor;

-- 创建用户表
CREATE TABLE users
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(50)  NOT NULL UNIQUE,       -- 用户名
    email      VARCHAR(100) NOT NULL UNIQUE,       -- 电子邮件
    password   VARCHAR(255) NOT NULL,              -- 密码哈希（存储加密后的密码）
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 用户创建时间戳
);

-- 创建健康数据种类表
CREATE TABLE health_metrics_types
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,                      -- 数据种类的名称
    description TEXT,                                       -- 数据种类的描述（可选）
    unit        VARCHAR(50),                                -- 数据单位（可选）
    data_type   ENUM('INTEGER', 'FLOAT', 'STRING') NOT NULL -- 数据类型
);

-- 创建健康数据记录表
CREATE TABLE health_metrics
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    user_id        INT NOT NULL,                                      -- 用户ID（外键，关联到users表）
    metric_type_id INT NOT NULL,                                      -- 数据种类ID（外键，关联到health_metrics_types表）
    value DOUBLE,                                                     -- 监控数据的值（使用DOUBLE类型以支持小数）
    recorded_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,               -- 记录时间戳
    FOREIGN KEY (user_id) REFERENCES users (id),                      -- 外键关联到用户表
    FOREIGN KEY (metric_type_id) REFERENCES health_metrics_types (id) -- 外键关联到数据种类表
);
