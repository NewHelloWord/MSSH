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

    <style>
        body{
            font-family: "Microsoft YaHei";
            margin: 0;
            padding: 0;
        }
        .errorMsg{
            color: red;
            display: none;
        }
    </style>
</head>
<body>
<!-- 代码 开始 -->
<div id="login">
    <div class="wrapper">
        <div class="login" style="background-color: bisque;">
            <form action="/toLogin.htm" method="post" onsubmit="return checkReg();" class="container offset1 loginform">
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
                            <label for="username" class="control-label fa fa-envelope"></label>
                            <input id="username" type="text" name="username" placeholder="userName" tabindex="1" autofocus="autofocus" class="form-control input-medium">
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
                            <img src="/code.htm" id="codeImg" style="width: 45%;height: 40px;float: right; margin-top: -40px;">
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <span iname="err" class="errorMsg">验证码错误</span>
                            <span style="color: red" id="emsg">${msg}</span>
                        </div>
                    </div>
                </div>

                <div class="form-actions"><a href="" tabindex="5" class="btn pull-left btn-link text-muted" style="color: #999;">忘记密码?</a><a href="/register.jsp" tabindex="6" class="btn btn-link text-muted" style="color: #999;">注册</a>
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

            $("#codeImg").on("click",function(){
                changeImg();
            });
        });

        function checkReg(){

            var username = $("#username").val();
            var password = $("#password").val();
            var flag = true;

            $(".errorMsg").hide();


            if(username.length == 0){
                $("span[iname='err']").text("用户名有误！").show();
                $("#emsg").hide();
                return false;
            }
            if(password.length == 0){
                $("span[iname='err']").text("密码有误！").show();
                $("#emsg").hide();
                return false;
            }

            $.ajax( {
                url : "verification.htm?r="+Math.random(),
                dataType : "json",
                type : "post",
                data : {"code":$("#code").val()},
                async:false,
                success : function(data) {
                    data = $.parseJSON(data);
                    console.log(data);
                    console.log(data.success);
                    if(!data.success) {
                        $("#emsg").hide();
                        $("span[iname='err']").text("验证码错误！").show();

                        flag = false;
                        changeImg();
                    }
                },
                error : function(data) {
                    alert("error");
                    alert(data.success);
                }
            });
            return flag;
            $("#emsg").show();

        }

        function changeImg(){
            var imgObj = document.getElementById("codeImg");
            imgObj.src = "code.htm?ran=" + Math.random();
        }

    </script>
</div>
<!-- 代码 结束 -->

</body>
</html>
