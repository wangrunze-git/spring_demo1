<%--
  Created by IntelliJ IDEA.
  User: wangrunze
  Date: 2021/4/13
  Time: 下午6:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Session</title>
</head>
<body>
    <%
        //session记录数据
        session.setAttribute("id", 1L);
        //转发URL
        response.sendRedirect("./session/test");
    %>
</body>
</html>
