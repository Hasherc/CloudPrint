<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Print +</title>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <title></title>
    <link rel="stylesheet" href="/resources/css/style.css">


</head>
<body background="/resources/images/25076907_1377825550765.jpg">
<nav class="navbar navbar-default" role="navigation">
    <div class=" container ">
        <div class="navbar-default ">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Print +</a>
            <a class="navbar-brand" href="/"><img width="100" src="/resources/images/7c96b27898c256a55929b81d7830e03c.png"alt="Logo white"></a>

        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a class="myNav" href="#">打印</a></li>
                <li><a class="myNav" href="#">订单信息</a></li>
                <li><a class="myNav" href="#">关于我们</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li id="registerCoin"><a href="/toRegister"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
                <li id="loginCoin"><a href="/toLogin"><span class="glyphicon glyphicon-log-in"></span> 登录</a>
                <li id="logoutCoin" style="display : none"><a href="/logout"><span class="glyphicon glyphicon-log-in"></span> 注销</a>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="jumbotron"  id="jumb">
        <h2 class="text-gradient">请选择要打印的文件类型</h2>
        <div class="select-div" id="typeSelectFile" data-toggle="modal" href="#documentModal">
            <p class="selecttext">文档</p>
            <img id="fileIcon"  src="/resources/images/folder-icon.png" height="100" width="100"/>
        </div>
        <div class="select-div" id="typeSelectImg" data-toggle="modal" href="#documentModal">
            <p class="selecttext">图片</p>
            <img id="imgIcon" src="/resources/images/folder-picture-icon.png" height="100" width="100"/>
        </div>
    </div>
</div>

<div class="modal fade" id="documentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" >
                    请选择需要打印的文件
                </h4>
            </div>
            <div class="modal-body">
                <div id="notice" class="alert alert-warning" style="display: none"></div>
                <div id="success" class="alert alert-success" style="display: none"></div>
                <form id="document-form" class="form-horizontal" role="form" >
                    <label for="documentInputFile">上传文件</label>
                            <input type="file" id="documentInputFile" name="file">
                    <p class="help-block" id="typeHelp"></p>
                </form>
            </div>
            <img src="/resources/images/5-121204193934-51.gif" hidden id="imgWait" >
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" id="documentSubmit">
                    提交文件
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/resources/js/user.js"></script>

</body>
</html>