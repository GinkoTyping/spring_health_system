CREATE
    DATABASE IF NOT EXISTS health_monitor;

USE
    health_monitor;

CREATE TABLE users
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(50)  NOT NULL UNIQUE,
    email      VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE health_metrics_types
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    unit        VARCHAR(50),
    is_unique   TINYINT(1)   NOT NULL DEFAULT 0
);

CREATE TABLE health_metrics
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    user_id          INT          NOT NULL,
    metric_type_id   INT          NOT NULL,
    metric_type_name VARCHAR(100) NOT NULL,
    value            DOUBLE,
    recorded_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (metric_type_id) REFERENCES health_metrics_types (id)
);
