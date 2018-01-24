<%--
  Created by IntelliJ IDEA.
  User: hanchao
  Date: 2018/1/13
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登录页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<hr/>
<h3>重定向至页面</h3>
<a href="/login/redirect2jsp">redirect2jsp</a>
<hr/>
<h3>重定向请求，并传递参数</h3>
<form action="/login/login" method="post">
    用户名：<input type="text" name="name"/>
    <input type="submit" name="登录"/>
</form>
</body>
</html>
