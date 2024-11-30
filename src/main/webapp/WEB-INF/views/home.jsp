<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>天天安康</title>
</head>
<body>
<h1>你好 <u>${username}</u>！ 欢迎来到天天安康！</h1>
<table>
    <thead>
    <tr>
        <th>数据ID</th>
        <th>数据类型</th>
        <th>数值</th>
        <th>提交时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${list}">
        <tr>
            <td>${item.id}</td>
            <td>${item.metricTypeName}</td>
            <td>${item.value}</td>
            <td>${item.recordedAt}</td>
            <td style="width: 100px">
                <form action="/health-metrics/update/${item.id}" method="get">
                    <!-- 表单字段，如果需要的话 -->
                    <button type="submit">编辑</button>
                </form>
                <form action="/health-metrics/delete/${item.id}" method="post">
                    <!-- 表单字段，如果需要的话 -->
                    <button type="submit">删除</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>${message}</p>

</body>
</html>

<style>
    h1 {
        text-align: center;
    }

    table {
        width: 600px;
        margin: 0 auto;
        border-collapse: collapse;
    }

    th, td {
        border: 1px solid #000;
        padding: 8px;
        text-align: left;
    }

    th {
        background-color: #f2f2f2;
    }
    form {
        display: inline-block;
    }
</style>