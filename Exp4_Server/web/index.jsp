<%@ page import="bean.Database" %><%--
  Created by IntelliJ IDEA.
  User: Alcumn
  Date: 2020/12/16
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script src="js/login&forget.js" type="text/javascript"></script>
    <script src="js/validator.js" type="text/javascript"></script>
  </head>
  <body>
  <div class="container">
    <%
      if (session.getAttribute("database") == null) {
        Database db = new Database();
        session.setAttribute("database", db);
      }
    %>
    <script language="JavaScript">
      sessionStorage.setItem("test", "1");
    </script>
    <br>
    <center><h4>Welcome to Moyu's World</h4></center>
    <br>
    <form class="form-horizontal" role="form" id="form-users">
      <div class="form-group">
        <label for="username_user" class="col-sm-4 control-label">Username</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" id="username_user"
                 name="username_user" placeholder="Please input username">
        </div>
      </div>
      <div class="form-group">
        <label for="password_user" class="col-sm-4 control-label">Password</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" id="password_user"
                 name="password_user" placeholder="Please input password">
        </div>
      </div>
      <div class="form-group" align="center">
          <button type="submit" class="btn btn-default" id="btn-login" name="btn-login">Login</button>
      </div>
    </form>
    <div class="form-group" align="center">
      <button type="submit" class="btn btn-default" id="btn-register" name="btn-register"
              onclick="window.location.href='./register.jsp'">Register</button>
    </div>
    <div class="form-group" align="center">
      <button type="submit" class="btn btn-default" id="btn-forget" name="btn-forget"
              onclick="window.location.href='./forget.jsp'">Forget Password</button>
    </div>
  </div>
  </body>
</html>
