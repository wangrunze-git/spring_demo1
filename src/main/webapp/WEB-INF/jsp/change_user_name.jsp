<%--
  Created by IntelliJ IDEA.
  User: wangrunze
  Date: 2021/4/14
  Time: 下午6:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>表单定义HTTP动作</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
    <form id="form" action="./name" method="post">
        <table>
            <tr>
                <td>用户编号</td>
                <td><input id="id" name="id"/></td>
            </tr>
            <tr>
                <td>用户名称</td>
                <td><input id="userName" name="userName"></td>
            </tr>
            <tr>
                <td></td>
                <td align="right">
                    <input id="submit" name="sumbit" type="submit">
                </td>
            </tr>
        </table>
        <input type="hidden" name="_method" id="_method" value="PATCH"/>
    </form>

</body>
</html>
