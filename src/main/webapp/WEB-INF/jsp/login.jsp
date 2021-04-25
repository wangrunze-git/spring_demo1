<%--
  Created by IntelliJ IDEA.
  User: wangrunze
  Date: 2021/4/16
  Time: 下午2:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>自定义登录表单</title>
</head>
<body>
    <form action="/login/page" method="POST">
        <p>名称：<input id="username" name="username" type="text" value=""></p>
        <p>描述：<input id="password" name="password" type="password" value=""></p>
        <p>记住我：<input id="remember_me" name="remember-me" type="checkbox"></p>
        <p><input type="submit" value="登录"></p>
        <input type="hidden" id="${_csrf.parameterName}" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
</body>
</html>
