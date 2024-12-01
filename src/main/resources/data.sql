INSERT INTO users (username, email, password)
VALUES ('1', '1-email', '1'),
       ('2', '2-email', '1');

INSERT INTO health_metrics_types (name, description, unit, is_unique)
VALUES ('身高', '您的身高', 'cm', 1),
       ('体重', '您的体重', 'kg', 1),
       ('步数', '您运动的总计步数', '步', 0);

INSERT INTO health_metrics (user_id, metric_type_id, metric_type_name, value)
VALUES (1, 1, '身高', 177.0),
       (1, 2, '体重', 70.0),
       (1, 3, '步数', 5000.0)
