<%--
  Created by IntelliJ IDEA.
  User: wangrunze
  Date: 2021/4/12
  Time: 下午2:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试JSR-303</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            //请求验证的POJO
            var pojo = {
                id : null,
                data : '2017-08-08',
                doubleValue : 999999.09,
                integer : 1000,
                emial : 'email',
                size : 'adv1212',
                regexp : 'a,b,c,d'
            }
            $.post({
                url : "./validate",
                //此处需要告知传递参数类型为JSON，不能缺少
                contentType : "application/json",
                data : JSON.stringify(pojo),
                //成功后的方法
                success : function (result) {

                }
            })
        })
    </script>
</head>
<body>

</body>
</html>
