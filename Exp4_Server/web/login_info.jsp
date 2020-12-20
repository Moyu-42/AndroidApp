
<%--
  Created by IntelliJ IDEA.
  User: Alcumn
  Date: 2020/12/16
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UserInfo</title>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.2/jquery.validate.min.js"></script>
</head>
<body>
<div class="container">
    <c:set var="person" value="${person}"/>
    <center><h5>Login User: ${person.username}</h5></center><br>
    <center><h4>Hello ${person.name}!</h4></center><br>
    <center><h4>Your Info:</h4></center><br>
    <ul class="list-inline">
        <center><li>Age: ${person.age}</li></center><br>
        <center><li>Telephone number: ${person.teleno}</li></center><br>
    </ul>
    <div class="form-group" align="center">
        <button type="submit" class="btn btn-default" id="btn-logout" name="btn-logout"
                onclick="window.location.href='./index.jsp'">Logout</button>
    </div>
    <div class="form-group" align="center">
        <button type="submit" class="btn btn-default" id="btn-passwd" name="btn-passwd"
                onclick="window.location.href='./change_password.jsp'">Change Password</button>
    </div>
    <div class="form-group" align="center">
        <button type="submit" class="btn btn-default" id="btn-edit" name="btn-edit"
                onclick="window.location.href='./modify.jsp'">Edit Info</button>
    </div>
</div>
</body>
</html>
