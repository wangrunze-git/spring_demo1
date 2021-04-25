<%--
  Created by IntelliJ IDEA.
  User: wangrunze
  Date: 2021/4/14
  Time: 上午10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Restful</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script type="text/javascript">
        $(function () {
            // post();
            // get();
            // users();
            // post();
            // updateUser();
            // patchUser();
            // deleteUser();
            postStatus();
        })
        function post() {
            var params = {
                'userName' : 'user_name_new_1',
                'sexCode' : 1,
                'note' : "note_new_1"
            }
            $.post({
                url : "./user",
                //此处需要告知传递参数类型为JSON，不可缺少
                contentType : "application/json",
                //将JSON转化为字符串传递
                data : JSON.stringify(params),
                //成功后的方法
                success : function (result) {
                    if (result == null || result.id == null){
                        alert("插入失败");
                        return;
                    }
                    alert("插入成功");
                }
            })
        }

        function get() {
            $.get("./user/1", function (user, status) {
                if (user == null){
                    alert("结果为空");
                }else {
                    alert("用户信息为" + JSON.stringify(user));
                }
            })
        }

        function users() {
            $.get("./users/user_/note/1/2", function (user, status) {
                if (users == null || users.size == 0){
                    alert("结果为空");
                }else {
                    alert("用户信息为" + JSON.stringify(user));
                }
            })
        }

        function updateUser() {
            var params = {
                'userName' : 'user_name_update',
                'sexCode' : 1,
                'note' : 'note_update'
            }
            $.ajax({
                url : "./user/1",
                //此处告知使用PUT请求
                type : "PUT",
                //此处需要告知传递参数类型为JSON，不能缺少
                contentType : "application/json",
                //将JSON转化为字符串传递
                data : JSON.stringify(params),
                success : function (user, status) {
                    if (user == null){
                        alert("结果为空")
                    }else{
                        alert(JSON.stringify(user));
                    }
                }

            })
        }

        function patchUser() {
            $.ajax({
                url : "./user/1/user_name_patch",
                //此处告知使用PUT请求
                type : "PATCH",
                //此处需要告知传递参数类型为JSON，不能缺少
                contentType : "application/json",
                success : function (result) {
                    if (!result.success){
                        alert("更新失败")
                    }else{
                        alert("更新成功");
                    }
                }
            })
        }

        function deleteUser() {
            $.ajax({
                url : "./user/1",
                type : 'DELETE',
                success : function (result) {
                    if (!result.success){
                        alert("删除失败")
                    }else{
                        alert("删除成功");
                    }
                }
            })
        }

        function postStatus() {
            //请求体
            var params = {
                'userName' : 'user_name_new',
                'sexCode' : 1,
                'note' : 'note_new'
            }
            var url = './user2/entity';
            // var url = './user2/annotation';
            $.post({
                url : url,
                //此处需要告知传递参数类型为JSON，不能缺少
                contentType : "application/json",
                //将JSON转化为字符串传递
                data : JSON.stringify(params),
                //成功后的方法
                success : function (result, status, jqXHR) {
                    //获取响应头
                    var success = jqXHR.getResponseHeader("success");
                    //获取状态码
                    var status = jqXHR.status;
                    alert("响应头参数是：" + success + ",状态码是：" + status);
                    if(result == null || result.id == null){
                        alert("插入失败");
                        return;
                    }
                    alert("插入成功");
                }
            })
        }






    </script>
</head>
<body>
    <h6>Restful下的请求</h6>
</body>
</html>
