INSERT INTO users (username, email, password)
VALUES ('1', '1-email', '1'),
       ('1', '1-email', '1');

INSERT INTO health_metrics_types (name, description, unit, data_type)
VALUES ('步数', '总计步数', 'steps', 'INTEGER'),
       ('心率', '每分钟心率', 'bpm', 'FLOAT'),
       ('血压', '平均血压', 'mmHg', 'FLOAT');

INSERT INTO health_metrics (user_id, metric_type_id, metric_type_name, value)
VALUES (1, 1, '步数',3000);
INSERT INTO health_metrics (user_id, metric_type_id, metric_type_name, value)
VALUES (1, 2, '心率',70.0);
INSERT INTO health_metrics (user_id, metric_type_id, metric_type_name, value)
VALUES (1, 3, '血压',100.0);