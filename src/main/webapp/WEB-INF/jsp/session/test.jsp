<%@ page import="com.springboot.chapter8.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: wangrunze
  Date: 2021/4/13
  Time: 下午9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试@SessionAttributes</title>
</head>
<body>
    <%
        //从Session中获取数据
        User user = (User) session.getAttribute("user");
        Long id = (Long) session.getAttribute("id_new");
        //展示数据
        out.print("<br>user_name = " + user.getUserName());
        out.println("<br>id_name = " + id);
    %>
</body>
</html>
