<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>天天安康</title>
</head>
<body>
<h2>添加健康数据种类</h2>
<form action="/health-metrics-type/add" method="post">
    <label for="name">种类名称:</label>
    <input class="inputs" type="text" id="name" name="name" required>
    <br>
    <label for="description">种类简介:</label>
    <input class="inputs" type="text" id="description" name="description" required>
    <br>
    <label for="unit">单位:</label>
    <input class="inputs" type="text" id="unit" name="unit" required>
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