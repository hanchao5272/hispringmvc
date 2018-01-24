<%--
  Created by IntelliJ IDEA.
  User: hanchao
  Date: 2018/1/14
  Time: 0:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>@RequestHeader</title>
</head>
<body>
    <h4>Accept：${accept}</h4>
    <h4>Accept-Encoding：${encoding}</h4>
    <h4>Accept-Language：${language}</h4>
    <h4>Connection：${alive}</h4>
    <h4>Cookie：${cookie}</h4>
    <h4>Host：${host}</h4>
    <h4>Referer：${referer}</h4>
    <h4>Upgrade-Insecure-Requests：${upgrade}</h4>
    <h4>User-Agent：${agent}</h4>
</body>
</html>
