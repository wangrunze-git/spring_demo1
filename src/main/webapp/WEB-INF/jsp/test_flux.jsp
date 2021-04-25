<%--
  Created by IntelliJ IDEA.
  User: wangrunze
  Date: 2021/4/23
  Time: 下午5:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>1231111</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script type="text/javascript">
        $(function () {
            var data = {
                'id' : 20,
                'userName' : 'test_20',
                'note' : 'note_20'
            };
            $.ajax({
                url : "http://localhost:8080/user/updateUser",
                data : JSON.stringify(data),
                async : true,
                type : 'put',
                success : function (result) {
                    if (result != null){
                        alert('插入成功');
                    }
                }
            })
        })

    </script>
</head>
<body>

</body>
</html>
