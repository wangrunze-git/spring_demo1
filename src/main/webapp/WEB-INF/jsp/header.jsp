<%--
  Created by IntelliJ IDEA.
  User: wangrunze
  Date: 2021/4/13
  Time: 下午10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取请求头参数</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script type="text/javascript">
        $.post({
            url : "./user",
            //设置请求头参数
            headers : {id : '1'},
            //成功后的方法
            success : function (user) {
                if (user == null || user.id == null){
                    alert("获取失败")
                    return
                }
                //弹出请求返回的用户信息
                alert("id=" + user.id + ",user_name=" + user.userName + ",note=" + user.note);
            }
        })
    </script>

</head>
<body>

</body>
</html>
