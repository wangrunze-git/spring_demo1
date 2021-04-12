<%--
  Created by IntelliJ IDEA.
  User: wangrunze
  Date: 2021/4/10
  Time: 下午5:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>用户详情</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
    <center>
        <table border="1">
            <tr>
                <td>标签</td>
                <td>值</td>
            </tr>
            <tr>
                <td>用户编号</td>
                <td><c:out value="${user.id}"></c:out></td>
            </tr>
            <tr>
                <td>用户名称</td>
                <td><c:out value="${user.userName}"></c:out></td>
            </tr>
            <tr>
                <td>用户备注</td>
                <td><c:out value="${user.note}"></c:out></td>
            </tr>
        </table>
    </center>
</body>
</html>
