<%--
  Created by IntelliJ IDEA.
  User: hanchao
  Date: 2018/1/25
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<%--拦截器--%>
<h3>拦截器</h3><hr/>
<a href="/hello">hello</a><br/>

<input type="button" onclick="world()" value="world"/>

<form action="/logout" method="post">
    <input type="submit" value="登出"/>
</form>

</body>
<script type="application/javascript" src="static/js/jquery.js"></script>
<script type="application/javascript">
    function world() {
        $.ajax({
            url:"/world",
            type:"post",
            data:JSON.stringify({
                username:'0001',password:'zhangsan'
            }),
            contentType:"application/json;charset=utf-8",
            success:function (data) {
                alert("hello world!");
                location.href = "/interceptor/hello.jsp";
            },
            error:function (data) {
                if (-1 == data.code){
                    alert(data.message);
                    location.href = "/login.jsp";
                }
            }
        });
    }
</script>
</html>
