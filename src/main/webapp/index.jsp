<%--
  Created by IntelliJ IDEA.
  User: Yao
  Date: 2016/8/1
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Okami</title>
    <style>
        body{
            font-family: "Microsoft YaHei";
            margin: 0;
            padding: 0;
        }
        a{
            text-decoration: none;
        }
        .content{
            width: 800px;
            height: 400px;
            margin: 80px auto;
            background-color: aquamarine;
        }
        input[name="code"]{
            width: 60px;
        }
        form img{
            width: 100px;
            height: 40px;
        }
        .errorMsg{
            padding-left:5px;
            color: red;
            display: none;
        }
    </style>

</head>
<body>
    <div class="content">
        <div>
            <form action="/regist.htm" method="post" onsubmit="return reg();">
                <div>
                    <span>用户名：</span><input type="text" name="username" id="name">
                    <span iname="name" class="errorMsg">用户名有误</span><br/>

                    <span>密码&nbsp;&nbsp;&nbsp;：</span><input type="password" name="password" id="password">
                    <span iname="password" class="errorMsg">密码有误</span><br/>

                    <span>验证码：</span><input type="text" name="code" id="code">
                    <img src="/code.htm" id="codeImg">
                    <span iname="code" class="errorMsg">验证码有误</span><br/>
                    <br/>
                    <input type="submit" value="注册">
                    <span id="aaa">aaaaaaaaa</span>
                    <a href="">去登陆</a>
                </div>


            </form>

        </div>

    </div>

</body>

<script type="text/javascript" src="<%=basePath%>/resources/js/jquery-1.9.1.js"></script>
<script type="text/javascript">

    $(function(){
        $("#codeImg").on("click",function(){
            changeImg();
        });

        $("#aaa").on("click",function(){
            toReg();
        });
    });

    function reg(){

        var name = $("#name").val();
        var password = $("#password").val();
        var flag = true;

        $(".errorMsg").hide();

        if(name.length == 0){
            $("span[iname='name']").show();
            return false;
        }
        if(password.length == 0){
            $("span[iname='password']").show();
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
                    $("span[iname='code']").show();
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
    }


    function changeImg()
    {
        var imgObj = document.getElementById("codeImg");
        imgObj.src = "code.htm?ran=" + Math.random();
    }
</script>

</html>
