<%--
  Created by IntelliJ IDEA.
  User: Alcumn
  Date: 2020/12/16
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Moyu's World</title>
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
    <script src="js/modify.js" type="text/javascript"></script>
    <script src="js/validator.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <br>
    <center> <h4>个人信息修改</h4></center>
    <br>
    <c:set var="person" value="${sessionScope.person}"/>
    <center><h4>User: ${person.username}</h4></center><br>
    <form class="form-horizontal" role="form" id="form-modify"
          data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
          data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
          data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
        <div class="form-group">
            <label for="name_modify" class="col-sm-4 control-label">Name</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="name_modify"
                       name="name_modify" placeholder="Please input name">
            </div>
        </div>
        <div class="form-group">
            <label for="age_modify" class="col-sm-4 control-label">Age</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="age_modify"
                       name="age_modify" placeholder="Please input age">
            </div>
        </div>
        <div class="form-group">
            <label for="teleno_modify" class="col-sm-4 control-label">Teleno</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="teleno_modify"
                       name="teleno_modify" placeholder="Please input teleno">
            </div>
        </div>
        <div class="form-group" align="center">
            <div class="col-sm-offset-1 col-sm-10">
                <button type="submit" class="btn btn-default" id="btn-update" name="btn-update">Submit</button>
            </div>
        </div>
    </form>
    <div class="form-group" align="center">
        <div class="col-sm-offset-1 col-sm-10">
            <button type="submit" class="btn btn-default" id="btn-cancel" name="btn-cancel"
                    onclick="window.location.href='./login_info.jsp'">Cancel</button>
        </div>
    </div>
</div>
</body>
</html>
