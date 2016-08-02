<%--
  Created by IntelliJ IDEA.
  User: Yao
  Date: 2016/8/2
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="/resources/css/lrtk.css">

</head>
<body>
<!-- 代码 开始 -->
<div id="login">
    <div class="wrapper">
        <div class="login" style="background-color: bisque;">
            <form action="" method="post" class="container offset1 loginform">
                <div id="owl-login">
                    <div class="hand"></div>
                    <div class="hand hand-r"></div>
                    <div class="arms">
                        <div class="arm"></div>
                        <div class="arm arm-r"></div>
                    </div>
                </div>
                <div class="pad">
                    <div class="control-group">
                        <div class="controls">
                            <label for="email" class="control-label fa fa-envelope"></label>
                            <input id="email" type="email" name="email" placeholder="Email" tabindex="1" autofocus="autofocus" class="form-control input-medium">
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <label for="password" class="control-label fa fa-asterisk"></label>
                            <input id="password" type="password" name="password" placeholder="Password" tabindex="2" class="form-control input-medium">
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <%--<label for="password" class="control-label fa fa-asterisk"></label>--%>
                            <input id="code" type="text" name="code" placeholder="Code" tabindex="3" class="form-control input-medium" style="width: 48%">
                            <img src="" style="width: 45%;height: 40px;float: right; margin-top: -40px;">
                        </div>
                    </div>
                </div>
                <div class="form-actions"><a href="" tabindex="5" class="btn pull-left btn-link text-muted" style="color: #999;">忘记密码?</a><a href="" tabindex="6" class="btn btn-link text-muted" style="color: #999;">注册</a>
                    <button type="submit" tabindex="4" class="btn btn-primary">登录</button>
                </div>
            </form>
        </div>
    </div>


    <div style="text-align:center;height: 260px;background-color: cadetblue;">
        <p style="margin:20px 0"></p>
    </div>

    <script src="/resources/js/jquery-1.9.1.js"></script>
    <script>
        $(function() {

            $('#login #password').focus(function() {
                $('#owl-login').addClass('password');
            }).blur(function() {
                $('#owl-login').removeClass('password');
            });
        });
    </script>
</div>
<!-- 代码 结束 -->

</body>
</html>
