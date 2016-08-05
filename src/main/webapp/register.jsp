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
            <form action="/regist.htm" method="post" onsubmit="return checkReg();" class="container offset1 loginform">
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
                            <%--<img src="/code.htm" id="codeImg" style="width: 45%;height: 40px;float: right; margin-top: -40px;">--%>
                            <input id="phoneCode" class="icode" type="button" value="获取验证码" style="width: 45%;height: 40px;float: right; margin-top: -40px;border: 0;background-color: #6a8fb2;">
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <span iname="err" class="errorMsg">验证码错误</span>
                        </div>
                    </div>
                </div>
                <div class="form-actions"><a href="" tabindex="5" class="btn pull-left btn-link text-muted" style="color: #999;">忘记密码?</a><a href="/login.jsp" tabindex="6" class="btn btn-link text-muted" style="color: #999;">登录</a>
                    <button type="submit" tabindex="4" class="btn btn-primary">注册</button>
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

            $("#phoneCode").on("click",function(){

            });


        });

        var InterObj;
        var count = 120;
        var now;

        $(".icode").click(function(){
            if(!checkMobile()){return;}
            $(".icode").attr("disabled","disabled");

            //后台生成验证码
            $.ajax({
                type: 'POST',
                url: 'mobile.htm',
                data: {mobile:$.trim($("#username").val())},
                dataType:'json',
                success: function(data){
                    data = $.parseJSON(data);
                    if(data.success==true) {
                        now = count;
                        $("#code").val("");
                        $("#code").focus();
                        $(".icode").text("剩余 "+now+" s");
                        InterObj = setInterval(SetRemainTime,1000);
                    } else {
                        $(".icode").removeAttr("disabled");
                        $("span[iname='err']").text(data.msg).show();
//                        if(data.state==1) {
//                            $("#mInfo").show();
//                            $("#username").focus();
//                        }
                    }
                },
                error : function(strValue) {
                }
            });

        });

        function SetRemainTime(){
            if(now==0){
                clearInterval(InterObj);
                $(".icode").removeAttr("disabled").val("获取验证码");
            }else{
                now--;
                $(".icode").val("剩余 "+now+" s");
            }
        }


        //手机号码验证
        function checkMobile(){
            var mobile = $.trim($("#username").val());
            var reg = new RegExp("^1[3|4|5|8|7][0-9]\\d{8}$");
            if (false == reg.test(mobile)) {
                $("span[iname='err']").text("手机格式不正确！").show();
                $("#username").focus();
                return false;
            } else {
                $("span[iname='err']").hide();
                return true;
            }
        }

        //验证码验证
        function checkYzm(){
            var yzm = $("#code").val();
            if ($.trim(yzm).length<6) {
                $("#code").val("");
                $("#code").focus();
                $("span[iname='err']").text("请输入6位数字验证码！").show();
                return false;
            } else {
                $("span[iname='err']").hide();
                return true;
            }
        }

        function checkReg(){

            var username = $("#username").val();
            var password = $("#password").val();
            var yzm = $("#code").val();
            var flag = true;

            $(".errorMsg").hide();

            if(username.length == 0){
                $("span[iname='err']").text("用户名有误！").show();
                return false;
            }
            if(password.length == 0){
                $("span[iname='err']").text("密码有误！").show();
                return false;
            }
            if ($.trim(yzm).length<6) {
                $("#code").val("");
                $("#code").focus();
                $("span[iname='err']").text("请输入6位数字验证码！").show();
                return false;
            } else {
                $("span[iname='err']").hide();
                return true;
            }
            return flag;

        }


    </script>
</div>
<!-- 代码 结束 -->

</body>
</html>
