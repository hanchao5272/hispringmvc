<%--
  Created by IntelliJ IDEA.
  User: hanchao
  Date: 2018/1/20
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>题目信息管理</title>
    <style type="text/css">
        div{margin: 5px 5px; }
        .div-left {float: left;margin: 5px 5px;}
        .div-text-center {text-align:center; border:1px solid #96c2f1; background:#eff7ff;}
        textarea{border:1px solid #9bdf70;background:#f0fbeb}
        .in-text{width: 30px;}
    </style>
</head>
<body>
<div class="div-left">
    <textarea id="message" cols="50" rows="50"></textarea>
</div>
<div class="div-left">
    <div class="div-text-center">
        <label>题目id：</label><input class="in-text" type="text" id="id" value="1"/><input type="button" value="查询一个(GET)" onclick="question('GET')"/>
    </div>
    <div class="div-text-center">
        <label>题目列表</label><input type="button" value="查询所有(GET)" onclick="questions('GET')"/>
    </div>
    <div class="div-text-center">
        <table style="text-align: right">
            <tr>
                <td><label>id</label></td>
                <td><input id="new_id" type="text" value="1"/> </td>
            </tr>
            <tr>
                <td><label>title</label></td>
                <td><input id="new_title" type="text" value="新题目"/> </td>
            </tr>
            <tr>
                <td><label>score</label></td>
                <td><input id="new_score" type="text" value="100"/> </td>
            </tr>
            <tr>
                <td><label>answer</label></td>
                <td><input id="new_answer" type="text" value="新答案"/> </td>
            </tr>
            <tr>
                <td colspan="2"><input type="button" value="新增(POST)" onclick="question1('POST')"> </td>
            </tr>
            <tr>
                <td><input type="button" value="替换(PUT)" onclick="question1('PUT')"> </td>
                <td><input type="button" value="修改(PATCH)" onclick="question1('PATCH')"> </td>
            </tr>
        </table>
    </div>
    <div class="div-text-center">
        <label>题目id：</label><input class="in-text" type="text" id="del_id" value="1"/>
        <input type="button" value="删除一个(DELETE)" onclick="question('DELETE')">
    </div>
    <div class="div-text-center">
        <label>删除所有</label><td><input type="button" value="删除所有(DELETE)" onclick="questions('DELETE')">
    </div>
</div>
</body>
<script type="text/javascript" src="../static/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    /**
     * 获取一个题目：[GET]/restful/question/{id}
     */
    /**
     * 删除一个题目：[DELETE]/restful/question/{id}
     */
    function question(type) {
        $.ajax({
            type:type,
            url:"/restful/question/" + $("#id").val(),
            contentType:"application/json;charset=utf-8",
            success:function (data) {
                console.log(data);
                var html = "url[" + type + "]:/restful/question/" + $("#id").val() + "\n\nresult:\n" + JSON.stringify(data, null, 4);
                $("#message").html(html);
            }
        });
    }

    /**
     * 获取全部题目：[GET]/restful/question/
     */
    /**
     * 删除全部题目：[DELETE]/restful/question/
     */
    function questions(type) {
        $.ajax({
            type:type,
            url:"/restful/question/",
            contentType:"application/json;charset=utf-8",
            success:function (data) {
                console.log(data);
                var html = "url[" + type + "]:/restful/question/\n\nresult:\n" + JSON.stringify(data, null, 4);
                $("#message").html(html);
            }
        });
    }

    /**
     * 新增一个题目：[POST]/restful/question/
     */
    /**
     * 替换一个题目：[PUT]/restful/question/
     */
    /**
     * 修改一个题目：[PATCH]/restful/question/
     */
    function question1(type) {
        $.ajax({
            type:type,
            url:"/restful/question/",
            data:JSON.stringify({
                id:$("#new_id").val(),
                title:$("#new_title").val(),
                score:$("#new_score").val(),
                answer:$("#new_answer").val()
            }),
            contentType:"application/json;charset:utf-8",
            success:function (data) {
                console.log(data);
                var html = "url[" + type + "]:/restful/question/\n\nresult:\n" + JSON.stringify(data,null,4);
                $("#message").html(html);
            }
        });
    }
</script>
</html>
