<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>天天安康</title>
</head>
<body>
<h2>天天安康欢迎您! </h2>


<form action="/login" method="post">
    <label for="username">账户:</label>
    <input class="inputs" type="text" id="username" name="username" required>
    <br>
    <label for="password">密码:</label>
    <input class="inputs" type="password" id="password" name="password" required>
    <br>
    <input id="loginBtn" type="submit" value="登录">
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