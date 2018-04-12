<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hanchao
  Date: 2018/1/22
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>文件上传下载</title>
    <style type="text/css">
        div {margin: 5px 5px;text-align:center; border:1px solid #96c2f1; background:#eff7ff;}
    </style>
</head>
<body>
<div style="width: 600px;">
    <h3>文件上传和下载实例</h3>
</div>
<div style="width: 290px;height: 180px;float: left;">
    <%--一定要注意设置enctype="multipart/form-data"--%>
    <form method="post" action="/file/upload" enctype="multipart/form-data">
        <label>单个文件上传</label><hr/>
        <input type="file" name="file"/><hr/>
        <input type="submit" value="上传"/>
    </form>
</div>
<div style="width: 290px;height: 180px;float: left">
    <%--一定要注意设置enctype="multipart/form-data"--%>
    <form method="post" action="/file/upload" enctype="multipart/form-data">
        <label>多个文件上传</label><hr/>
        <%--一定要注意name="file"要与后台传参对应--%>
        <input type="file" name="file"/><br/>
        <input type="file" name="file"/><br/>
        <input type="file" name="file"/><br/>
        <input type="file" name="file"/><br/><hr/>
        <input type="submit" value="上传"/>
    </form>
</div>
<div style="width: 600px;">
    <small><b>${returnMessage}</b></small>
    <c:forEach items="${savedFileList}" var="file">
        <small><hr/>已上传：${file} </small>
    </c:forEach>
</div>
<div style="width: 600px;">
    <label>文件下载</label><hr/>
    <a href="/file/download/5/jpg/inline">在浏览器打开5.jpg</a><hr/>
    <a href="/file/download/11/xml/inline">在浏览器打开11.xml</a><hr/>
    <a href="/file/download/1/txt/inline">在浏览器打开1.txt</a><hr/>
    <a href="/file/download/5/jpg/attachment">下载5.jpg</a><hr/>
    <a href="/file/download/11/xml/attachment">下载11.xml</a><hr/>
    <a href="/file/download/1/txt/attachment">下载1.txt</a>
</div>
<div>

</div>

</body>
</html>
