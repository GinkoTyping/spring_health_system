-- 插入一些示例用户（可选）
INSERT INTO users (username, email, password)
VALUES ('1', '1-email', '1'),
       ('1', '1-email', '1');

-- 插入一些示例数据种类（可选）
INSERT INTO health_metrics_types (name, description, unit, data_type)
VALUES ('Steps', 'Number of steps taken', 'steps', 'INTEGER'),
       ('Heart Rate', 'Heart rate in beats per minute', 'bpm', 'FLOAT'),
       ('Blood Pressure', 'Blood pressure reading in mmHg', 'mmHg', 'STRING');
-- 假设血压以字符串形式存储，如"120/80"
