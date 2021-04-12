<%--
  Created by IntelliJ IDEA.
  User: wangrunze
  Date: 2021/4/9
  Time: 上午11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello Spring Boot</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script type="text/javascript">
        <!--后面在此处加入JavaScript脚本-->
        function post(user) {
            var url = "./save";
            $.post({
                url : url,
                //此处需要告知传递参数类型为JSON，不能缺少
                contentType : "application/json",
                //将JSON转化为字符串传递
                data : JSON.stringify(user),
                //成功后的方法
                success : function (result, status) {
                    if (result == null || result.id == null){
                        alert("插入失败");
                        return;
                    }
                }
            })
        }
        for (var i = 1; i <= 10 ; i++) {
            var user = {
                'id' : i,
                'userName' : 'user_name_' + i,
                'note' : 'note' + i,
                'roles' : [{
                    'id' : i,
                    'roleName' : 'role_' + i,
                    'note' : 'note_' + i
                },{
                    'id' : i + 1,
                    'roleName' : 'role_' + (i + 1),
                    'note' : 'note_' + (i + 1)
                }]
            }
            post(user);
        }
    </script>
</head>
<body>
    <h1>操作MongoDB文档</h1>
</body>
</html>
