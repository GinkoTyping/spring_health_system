<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>天天安康</title>
</head>
<body>
<h1>你好 <u>${username}</u>！ 欢迎来到天天安康！</h1>

<div class="search-type-container">
    <form action="/health-metrics/search-type" method="post">
        <div>
            <label for="metricsTypeId">健康数据类型:</label>
            <input class="inputs" type="text" id="metricsTypeId" name="metricsTypeId" required>
        </div>
        <button type="submit">按健康数据种类查看</button>
    </form>
    <form action="/health-metrics/search-username" method="post">
        <div>
            <label for="username">用户名称:</label>
            <input class="inputs" type="text" id="username" name="username" value="${healthMetrics.value}" required>
        </div>
        <button type="submit">按用户名称查看</button>
    </form>
</div>

<div style="width:600px; margin: 0 auto">
    <h3>一、 健康数据</h3>
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
        <c:forEach var="item" items="${metricsList}">
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
                    <form action="/health-metrics/delete/${item.id}/${item.metricTypeId}" method="post">
                        <!-- 表单字段，如果需要的话 -->
                        <button type="submit">删除</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form action="/health-metrics/add" method="get">
        <button type="submit">新增健康数据</button>
    </form>
    <form action="/health-metrics/advice" method="post">
        <button type="submit">给我提点建议</button>
    </form>
    <p>${metricsMessage}</p>
</div>

<div style="width:600px; margin: 0 auto; margin-top: 20px">
    <h3>二、 健康数据种类</h3>
    <table>
        <thead>
        <tr>
            <th>种类ID</th>
            <th>种类名称</th>
            <th>种类简介</th>
            <th>单位</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${metricsTypeList}">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.description}</td>
                <td>${item.unit}</td>
                <td style="width: 100px">
                    <form action="/health-metrics-type/update/${item.id}" method="get">
                        <button type="submit">编辑</button>
                    </form>
                    <form action="/health-metrics-type/delete/${item.id}" method="post">
                        <button type="submit">删除</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form action="/health-metrics-type/add" method="get">
        <button type="submit">新增健康数据类型</button>
    </form>
    <p>${metricsTypeMessage}</p>
</div>
</body>
</html>

<style>
    .search-type-container {
        width: 600px;
        margin: 0 auto;
        display: flex;
        align-items: center;
        flex-direction: column;
    }
    .search-type-container form {
        display: flex;
        align-items: center;
        justify-content: space-between;
        width: 450px;
    }
    .search-type-container form label {
        display: inline-block;
        width: 110px;
    }
    .search-type-container form button {
        margin-left: 8px;
        width: 140px;
    }
    p {
        color: red;
        font-weight: 600;
        text-decoration: underline;
        margin: 4px 0;
    }

    h1 {
        text-align: center;
    }

    table {
        width: 600px;
        margin: 0 auto;
        margin-bottom: 10px;
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