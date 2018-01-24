<%--
  Created by IntelliJ IDEA.
  User: hanchao
  Date: 2018/1/15
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>@ModelAttribute</title>
</head>
<body>
    <h3>@ModelAttribute     注解在void f()上：   ${voidmethod}</h3>
    <h3>@ModelAttribute     注解在Object f()上： ${user.sex}</h3>
    <h3>@ModelAttribute(...)注解在Object f()上： ${methoduser.sex}</h3>
    <h3>@ModelAttribute     注解在方法参数上：    ${parammeteruser.sex}</h3>
    <h3>@ModelAttribute     注解在方法参数上：    ${parammeteruser2.sex}</h3>
</body>
</html>
