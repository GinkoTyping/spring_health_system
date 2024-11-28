-- 插入一些示例用户（可选）
INSERT INTO users (username, email, password_hash)
VALUES ('alice', 'alice@example.com', 'hashed_password_for_alice'),
       ('bob', 'bob@example.com', 'hashed_password_for_bob');

-- 插入一些示例数据种类（可选）
INSERT INTO health_metrics_types (name, description, unit, data_type)
VALUES ('Steps', 'Number of steps taken', 'steps', 'INTEGER'),
       ('Heart Rate', 'Heart rate in beats per minute', 'bpm', 'FLOAT'),
       ('Blood Pressure', 'Blood pressure reading in mmHg', 'mmHg', 'STRING');
-- 假设血压以字符串形式存储，如"120/80"

-- 注意：在实际应用中，密码不应以明文形式存储，而应使用适当的哈希算法进行加密。
-- 上面的'hashed_password_for_alice'和'hashed_password_for_bob'只是占位符，
-- 你应该使用密码哈希库（如bcrypt）来生成和验证密码哈希。