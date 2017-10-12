<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Print + </title>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <title></title>
    <link rel="stylesheet" href="/resources/css/style.css">

</head>
<body background="/resources/images/25076907_1377825550765.jpg">
<nav class="navbar navbar-default" role="navigation">
    <div class="container ">
        <div class="navbar-default ">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse"
                    aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Print +</a>
            <a class="navbar-brand" href="/"><img width="100"
                                                  src="/resources/images/7c96b27898c256a55929b81d7830e03c.png"
                                                  alt="Logo white"></a>

        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse">

            <ul class="nav navbar-nav navbar-right">
                <li id="registerCoin"><a href="#"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
                <li id="loginCoin"><a href="/toLogin"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="well col-md-6" id="loginRegisterDiv">
            <br>
            <div id="notice" class="alert alert-warning" style="display: none" id="userNotice">
            </div>
            <form id="register" class="form-horizontal" role="form">
                <div class="form-group">
                    <label for="register_name" class="col-sm-2 control-label">
                        姓名
                    </label>
                    <div class="col-sm-6">
                        <input class="form-control" id="register_name" name="nickName" />
                    </div>
                    <div class="col-sm-4 help-block" style="font-size: smaller">请输入您的真实姓名</div>

                </div>
                <div class="form-group">
                    <label for="register_phoneNum" class="col-sm-2 control-label">
                        手机号
                    </label>
                    <div class="col-sm-6">
                        <input class="form-control" id="register_phoneNum" name="phoneNum"/>
                    </div>
                    <div class="col-sm-4 help-block" style="font-size: smaller">请输入您的13位手机号码</div>
                </div>
                <div class="form-group">
                    <label for="register_password" class="col-sm-2 control-label">
                        密码
                    </label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="register_password" name="password"/>
                    </div>
                    <div class="col-sm-4 help-block" style="font-size: smaller">以字母开头，长度在6~18之间，只能包含字符、数字和下划线。</div>
                </div>
                <div class="form-group">
                    <label for="repeat_password" class="col-sm-2 control-label">
                        再次输入密码
                    </label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="repeat_password" name="password"/>
                    </div>
                    <div class="col-sm-4 help-block" style="font-size: smaller"> 再次输入密码以确认</div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">

                        <button id="register_button" type="button" class="btn btn-default" onclick="sendRegisterForm()">
                            注册
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="/resources/js/user.js"></script>

</body>
</html>