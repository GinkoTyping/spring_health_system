<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>天天安康</title>
</head>
<body>
<h2>修改健康数据</h2>
<form action="/health-metrics/update/${id}" method="post">
    <label for="metricTypeId">数据类型ID:</label>
    <input class="inputs" type="text" id="metricTypeId" name="metricTypeId" required>
    <br>
    <label for="value">数值:</label>
    <input class="inputs" type="text" id="value" name="value" required>
    <br>
    <input id="loginBtn" type="submit">
    <p>${message}</p>
</form>
</body>

<style>
    h2, h3 {
        text-align: center;
    }

    form {
        width: 300px;
        margin: 0 auto;
    }

    form label {
        width: 90px;
        display: block;
    }

    form .inputs {
        width: 100%;
        box-sizing: border-box;
    }

    form #loginBtn {
        display: block;
        width: 100%;
        height: 30px;
        margin-top: 14px;
    }
</style>
</html>