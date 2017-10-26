function sendRegisterForm() {

    var model = {
        nickName: document.getElementById("register_name").value.trim(),
        phoneNum: document.getElementById("register_phoneNum").value.trim(),
        password: document.getElementById("register_password").value.trim(),
        repeat_password: document.getElementById("repeat_password").value.trim()
    };

    if (checkRegister(model)) {
        $.ajax({
            type: "post",
            url: "/register",
            data: JSON.stringify(model),
            dataType: "json",
            contentType: "text/plain",
            success: function (result) {
                if (result.status == 1) {
                    window.location.href = "/printSelect"
                }
                else if (result.status == 1007) {
                    $("#notice").show();
                    $("#notice").text("该手机号已被注册");

                }
                else {
                    $("#notice").show();
                    $("#notice").text("登录失败")
                }
            }
        });
    }
}

function sendLoginForm() {
    var model = {
        phoneNum: document.getElementById("login_phoneNum").value.trim(),
        password: document.getElementById("login_password").value.trim()
    };
    if (checkLogin(model)) {
        $.ajax({
            type: "post",
            url: "/loginByPhoneNum",
            data: JSON.stringify(model),
            dataType: "json",
            contentType: "text/plain",
            success: function (result) {
                if (result.status == 1) {
                    window.location.href = "/printSelect"
                }
                else if (result.status == 1004) {
                    $("#notice").show();
                    $("#notice").text("用户名或密码错误");

                }
                else {
                    $("#notice").show();
                    $("#notice").text("登录失败")
                }
            }
        });
    }

}

function checkLogin(model) {


        if (model.phoneNum == "") {
            $("#notice").show();
            $("#notice").text("手机号不能为空");

            return false;
        }

        if (model.password == "") {
            $("#notice").show();
            $("#notice").text("密码不能为空");
            return false;
        }

        if (!check_tel(model.phoneNum)){
            $("#notice").show();
            $("#notice").text("请输入正确的手机号");
            return false;
        }
        return true;
}

function check_tel(model) {
    var regx=/^(?:13\d|15\d|18[123456789])-?\d{5}(\d{3}|\*{3})$/;
    return regx.test(model);

}

function check_password(model) {
    var b=/^[a-zA-Z]\w{5,17}$/;
    if (model.length < 6 || model.length >18){
        return false;
    }
    if (!b.test(model)){
        return false;
    }
    return true;
}

function checkRegister(model) {

        if (model.nickName == "") {
            $("#notice").show();
            $("#notice").text("姓名不能为空");
            return false;
        }
        if (model.phoneNum == "") {
            $("#notice").show();
            $("#notice").text("手机号不能为空");
            return false;
        }
        if (!check_tel(model.phoneNum)){
            $("#notice").show();
            $("#notice").text("请输入正确的手机号");
            return false;
        }
        if (model.password == "") {
            $("#notice").show();
            $("#notice").text("密码不能为空");
            return false;
        }
        if (model.password != model.repeat_password) {
            $("#notice").show();
            $("#notice").text("两次密码不一致请重新输入");
            return false;
        }
        if (!check_password(model.password)) {
            $("#notice").show();
            $("#notice").text("请按要求输入密码");
            return false;
        }

        return true;
}

$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        $("#register_button").trigger("click");
    }
});

$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        $("#login_button").trigger("click");
    }
});
$(function (){
    $("[data-toggle='popover']").popover();
});


