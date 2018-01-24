<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<h2>Hello World!</h2>
<%--helloworld实例--%>
<a href="/helloworld">Hello World!</a><hr/>

<%--重定向实例--%>
<a href="redirect/login.jsp">redirect实例</a><hr/>

<%--@PathVariable实例--%>
<a href="/requestannotation/getpathvariable/张三/time/昨天">@PathVariable实例 : /requestannotation/getpathvariable/张三/time/昨天</a><hr/>

<%--@RequestHeader实例--%>
<a href="/requestannotation/requestheader">@RequestHeader实例</a><hr/>

<%--@RequestParam实例--%>
<a href="/requestannotation/getrequestparam?getname=张三">@RequestParam[GET]实例 : /requestannotation/getrequestparam?getname=张三</a><br>
<form action="/requestannotation/postrequestparam" method="post">
    <input value="李四" name="postname">
    <input type="submit" value="@RequestParam[POST]实例"/>
</form><hr/>

<%--@RequestBody实例--%>
<input type="button" onclick="requestbody()" value="@RequestBody[POST]实例"/>
<input type="text" id="requestbody" class="text"/>
<hr/>

<%--@SessionAttribute实例--%>
<a href="/requestannotation/setsession">@SessionAttribute实例[set]</a><br>
<a href="/requestannotation/getsession">@SessionAttribute实例[get]</a><br>
<a href="/requestannotation/delsession">@SessionAttribute实例[del]</a><br><hr/>

<%--@ModelAttribute实例--%>
<a href="/requestannotation/getmodelattribute?name=张三&sex=传参,会覆盖默认的ModelAttribut">@ModelAttribute[GET]实例</a><br>

<hr/>
<%--表单校验--%>
<a href="validation/student.jsp">表单校验</a>

<hr/>
<%--自定义视图解析器--%>
<a href="htmlviewresolver/index.jsp">自定义视图解析器</a>

<hr/>
<%--restful风格--%>
<a href="restful/question.jsp">restfull风格</a>

<hr/>
<%--freemarker--%>
<a href="freemarker/index.jsp">freemarker</a>

<%--文件上传与下载--%>
<a href="file/file.jsp">文件上传与下载</a>

</body>
<script type="text/javascript" src="static/jquery-3.2.1.min.js"></script>

<script type="text/javascript">
    //@RequestBody实例：必须指定contentType:application/json
    function requestbody() {
        $.ajax({
            type:"POST",
            url:"/requestannotation/requestbody",
            data:JSON.stringify(
                {name:"张三",sex:"男"}
            ),
            contentType:"application/json; charset=utf-8",
            success:function (data) {
                console.log(data);
                $("#requestbody").val(data.name + "是" + data.sex + "的");
            }
        });
    }
</script>
</html>
