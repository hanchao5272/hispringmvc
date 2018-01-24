<%--
  Created by IntelliJ IDEA.
  User: hanchao
  Date: 2018/1/17
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息管理页面</title>
    <script type="text/javascript" src="../static/jquery-3.2.1.min.js"></script>
    <style>
        table, td, th{border:1px solid green;background-color: lemonchiffon;text-align:center}
    </style>
</head>
<body>
<p id="errorString"/>
    <form>
        <table>
            <tr>
                <td><label>姓名</label></td>
                <td><input name="name" type="text"></td>
            </tr>
            <tr>
                <td><label>是否新注册用户</label></td>
                <td><input name="newRegister" type="text"></td>
            </tr>
            <tr>
                <td><label>学分</label></td>
                <td><input name="score" type="text"></td>
            </tr>
            <tr>
                <td><label>年龄</label></td>
                <td><input name="age" type="text"></td>
            </tr>
            <tr>
                <td><label>姓名</label></td>
                <td><input name="name" type="text"></td>
            </tr>
            <tr>
                <td><label>体重（kg）</label></td>
                <td><input name="weight" type="text"></td>
            </tr>
            <tr>
                <td><label>入学时间</label></td>
                <td><input name="entrance" type="text"></td>
            </tr>
            <tr>
                <td><label>毕业时间</label></td>
                <td><input name="graduation" type="text"></td>
            </tr>
            <tr>
                <td><label>学号</label></td>
                <td><input name="number" type="text"></td>
            </tr>
            <tr>
                <td><label>个人学生主页</label></td>
                <td><input name="blog" type="text"></td>
            </tr>
            <tr>
                <td><label>学费</label></td>
                <td><input name="tuition" type="text"></td>
            </tr>
            <tr>
                <td><label>奖金</label></td>
                <td><input name="bonus" type="text"></td>
            </tr>
            <tr>
                <td><label>银行卡号</label></td>
                <td><input name="creditCard" type="text"></td>
            </tr>

            <tr>
                <td><input type="button" value="注册" onclick="insert()"/></td>
                <td><input type="button" value="查询" onclick="update()"/></td>
            </tr>
        </table>
    </form>
</body>
<script type="text/javascript">
    //插入一条学生信息
    function insert() {
        $.ajax({
            type:"POST",
            url:"/validation/insert",
            data:JSON.stringify({
                name:$('input[name="name"]').val(),
                newRegister:$('input[name="newRegister"]').val(),
                score:$('input[name="score"]').val(),
                age:$('input[name="age"]').val(),
                weight:$('input[name="weight"]').val(),
                entrance:$('input[name="entrance"]').val(),
                graduation:$('input[name="graduation"]').val(),
                number:$('input[name="number"]').val(),
                reward:$('input[name="reward"]').val(),
                opinion:$('input[name="opinion"]').val(),
                email:$('input[name="email"]').val(),
                blog:$('input[name="blog"]').val(),
                tuition:$('input[name="tuition"]').val(),
                bonus:$('input[name="bonus"]').val(),
                creditCard:$('input[name="creditCard"]').val()
            }),
            contentType:'application/json;charset=utf-8',
            dataType:'json',
            success:function (jsonResult) {
                console.log(jsonResult);
                $("#errorString").html(jsonResult.message);
            }
        })
    }
    //更新学生信息
    function update() {
        $.ajax({
            type:"POST",
            url:"/validation/update",
            data:JSON.stringify({
                name:$('input[name="name"]').val(),
                newRegister:$('input[name="newRegister"]').val(),
                score:$('input[name="score"]').val(),
                age:$('input[name="age"]').val(),
                weight:$('input[name="weight"]').val(),
                entrance:$('input[name="entrance"]').val(),
                graduation:$('input[name="graduation"]').val(),
                number:$('input[name="number"]').val(),
                reward:$('input[name="reward"]').val(),
                opinion:$('input[name="opinion"]').val(),
                email:$('input[name="email"]').val(),
                blog:$('input[name="blog"]').val(),
                tuition:$('input[name="tuition"]').val(),
                bonus:$('input[name="bonus"]').val(),
                creditCard:$('input[name="creditCard"]').val()
            }),
            contentType:'application/json;charset=utf-8',
            dataType:'json',
            success:function (jsonResult) {
                console.log(jsonResult);
                $("#errorString").html(jsonResult.message);
            }
        })
    }
</script>
</html>
