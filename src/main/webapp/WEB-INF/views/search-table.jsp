<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>

<h2 style="text-align: center">${title}</h2>
<table>
    <thead>
    <tr>
        <th>用户ID</th>
        <th>数据类型</th>
        <th>数值</th>
        <th>提交时间</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${metricsList}">
        <tr>
            <td>${item.userId}</td>
            <td>${item.metricTypeName}</td>
            <td>${item.value}</td>
            <td>${item.recordedAt}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</html>

<style>
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
</style>