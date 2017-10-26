<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Print + </title>

    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <title></title>
    <link rel="stylesheet" href="/resources/css/style.css">

</head>
<body>
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
            <a class="navbar-brand" href="#">Print +</a>
            <a class="navbar-brand" href="#"><img width="100"
                                                  src="/resources/images/7c96b27898c256a55929b81d7830e03c.png"
                                                  alt="Logo white"></a>

        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a class="myNav" href="/printSelect">打印</a></li>
                <li><a class="myNav" href="#">订单信息</a></li>
                <li><a class="myNav" href="#">关于我们</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li id="registerCoin"><a href="/toRegister"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
                <li id="loginCoin"><a href="/toLogin"><span class="glyphicon glyphicon-log-in"></span> 登录</a>
                <li id="logoutCoin" style="display : none"><a href="/logout"><span
                        class="glyphicon glyphicon-log-in"></span> 注销</a>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-sm-8 col-sm-offset-2">
            <h3 class="text-center text-primary">订单编号:
                <span id="order_uuid"></span>
            </h3>
            <ul class="list-group" style="font-size: 22px;">
                <li class="list-group-item list-inline">
                    姓名:
                    <div class="pull-right">
                        <span id="order_user_name">
                            default
                        </span>
                        <a class="text-muted" style="font-size: small" data-toggle="modal" data-target="#user_name_editor">
                            编辑
                        </a>
                    </div>
                </li>
            </ul>
            <ul class="list-group" style="font-size: 20px;">
                <li class="list-group-item list-inline">
                    手机号:
                    <div class="pull-right">
                        <span id="order_phone_num">
                            default
                        </span>
                        <a class="text-muted" style="font-size: small" data-toggle="modal" data-target="#phone_num_editor">
                            编辑
                        </a>
                    </div>
                </li>
            </ul>
            <div class="order_file_back" style="display: none" id="order_file_first">
                <div class="row">
                    <div class="col-sm-10 text-muted">
                        <p style="font-size: small">文件名：<span id="order_first_file_name"></span></p>
                        <p style="font-size: small">文件类型：<span id="order_first_file_type"></span></p>
                        <p style="font-size: small">打印方式：<span id="order_first_print_way"></span></p>
                        <p style="font-size: small">色彩：<span id="order_first_print_color"></span></p>
                        <p style="font-size: small">页数：<span id="order_first_file_page"></span></p>
                    </div>
                    <div class="col-sm-1">
                        <button class="btn btn-default btn-sm" id="edit_file_first">编辑</button>
                    </div>
                </div>

            </div>
            <div class="order_file_back" style="display:none;" id="order_file_second">
                <div class="row">
                    <div class="col-sm-10 text-muted">
                        <p style="font-size: small">文件名：<span id="order_second_file_name"></span></p>
                        <p style="font-size: small">文件类型：<span id="order_second_file_type"></span></p>
                        <p style="font-size: small">打印方式：<span id="order_second_print_way"></span></p>
                        <p style="font-size: small">色彩：<span id="order_second_print_color"></span></p>
                        <p style="font-size: small">页数：<span id="order_second_file_page"></span></p>
                    </div>
                    <div class="col-sm-1">
                        <button class="btn btn-default btn-sm" id="edit_file_second">编辑</button>
                    </div>
                </div>
            </div>
            <div class="order_file_back" style="display:none;" id="order_file_third">
                <div class="row">
                    <div class="col-sm-10 text-muted">
                        <p style="font-size: small">文件名：<span id="order_third_file_name"></span></p>
                        <p style="font-size: small">文件类型：<span id="order_third_file_type"></span></p>
                        <p style="font-size: small">打印方式：<span id="order_third_print_type"></span></p>
                        <p style="font-size: small">色彩：<span id="order_third_print_color"></span></p>
                        <p style="font-size: small">页数：<span id="order_third_file_page"></span></p>
                    </div>
                    <div class="col-sm-1">
                        <button class="btn btn-default btn-sm" id="edit_file_third">编辑</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="user_name_editor" tabindex="-1" role="dialog" aria-labelledby="ModalLabel_one" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="ModalLabel_one">请输入您的姓名</h4>
                </div>
                <div class="modal-body">
                    <input class="form-control" id="user_name" value="">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="not_commit_name">关闭</button>
                    <button class="btn btn-default" id="commit_name">确定</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="phone_num_editor" tabindex="-1" role="dialog" aria-labelledby="ModalLabel_two" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="ModalLabel_two">
                        请输入您的联系方式
                    </h4>
                </div>
                <div class="modal-body">
                    <input class="form-control"  id="phone_num">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="not_commit_phone">关闭</button>
                    <button class="btn btn-default" id="commit_phone">确定</button>
                </div>

            </div>
        </div>
    </div>

</div>
<script src="/resources/js/jquery-3.2.1.min.js"></script>
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="/resources/js/global.js"></script>
<script src="/resources/js/user.js"></script>
<script src="/resources/js/order.js"></script>
</body>
</html>
