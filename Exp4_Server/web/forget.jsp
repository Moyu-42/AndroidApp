<%--
  Created by IntelliJ IDEA.
  User: Alcumn
  Date: 2020/12/17
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Forget Password</title>
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
    <script src="js/login&forget.js" type="text/javascript"></script>
    <script src="js/validator.js" type="text/javascript"></script>
</head>
<body>
<br>
<center><h4>Forget Password</h4></center>
<br>
<div class="container">
    <form class="form-horizontal" role="form" id="form-forget">
        <div class="form-group">
            <label for="username_forget" class="col-sm-4 control-label">Username</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="username_forget"
                       name="username_forget" placeholder="Please input username">
            </div>
        </div>
        <div class="form-group">
            <label for="password_new" class="col-sm-4 control-label">New Password</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="password_new"
                       name="password_new" placeholder="Please input password">
            </div>
        </div>
        <div class="form-group">
            <label for="password_confirm" class="col-sm-4 control-label">Confirm Password</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="password_confirm"
                       name="password_confirm" placeholder="Please input password again">
            </div>
        </div>
        <div class="form-group" align="center">
            <div class="col-sm-offset-1 col-sm-10">
                <button type="submit" class="btn btn-default" id="btn-change_forget" name="btn-change_forget">Submit</button>
            </div>
        </div>
    </form>
    <div class="form-group" align="center">
        <div class="col-sm-offset-1 col-sm-10">
            <button type="submit" class="btn btn-default" id="btn-cancel_passwd" name="btn-cancel_passwd"
                    onclick="window.location.href='./index.jsp'")>Cancel</button>
        </div>
    </div>
</div>
</body>
</html>
