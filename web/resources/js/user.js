// 用户模块
$(document).ready(function () {
    $.ajax({
        type: "post",
        url: "checkLogin",
        dataType: "json",
        contentType: "text/plain",
        success: function (result) {
            if (result.status == 1) {
                $("#loginCoin").hide();
                $("#registerCoin").hide();
                $("#logoutCoin").show();

            } else {

            }
        }
    });
});
//选择打印方式按钮效果
$(document).ready(function () {
    $("#typeSelectFile").hover(function () {
        $("#typeSelectFile").addClass("select-div2");
    }, function () {
        $("#typeSelectFile").removeClass("select-div2");
    });
    $("#typeSelectImg").hover(function () {
        $("#typeSelectImg").addClass("select-div2");
    }, function () {
        $("#typeSelectImg").removeClass("select-div2");
    });
});
$('#typeSelectFile').click(function () {
    $("#notice").hide();
    $("#success").hide();
    $("#typeHelp").text("暂支持文件类型：.doc .docx .ppt");
})
$('#typeSelectImg').click(function () {
    $("#notice").hide();
    $("#success").hide();
    $("#typeHelp").text("暂支持文件类型：.jpg .png ");
})

function sendLoginForm() {
    var model = {
        phoneNum: document.getElementById("login_phoneNum").value.trim(),
        password: document.getElementById("login_password").value.trim()
    };
    if (checkLogin(model)) {
        $.ajax({
            type: "post",
            url: "loginByPhoneNum",
            data: JSON.stringify(model),
            dataType: "json",
            contentType: "text/plain",
            success: function (result) {
                if (result.status == 1) {
                    window.location.href = "/"
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
            url: "register",
            data: JSON.stringify(model),
            dataType: "json",
            contentType: "text/plain",
            success: function (result) {
                if (result.status == 1) {
                    window.location.href = "/"
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


    // 注册校验
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


$(function () {
    $("#documentSubmit").click(function () {
        $("#notice").hide();
        $("#success").hide();
        var formData = new FormData($('#document-form')[0]);
        if($('#documentInputFile').val() == ""){
            $("#notice").show();
            $("#notice").text("请选择文件");
            return false;
        };
        $("#imgWait").show();
        $.ajax({
            url : 'file/upload',
            type : 'post',
            cache : false,
            data : formData,
            processData: false,
            contentType: false,
            success : function (data) {
                if (data.status == 1){
                    $("#success").show();
                    $("#success").text("文件上传成功");
                    $("#imgWait").hide();
                }
                if (data.status == 1008){
                    $("#notice").show();
                    $("#notice").text("请选择文件");
                    $("#imgWait").hide();
                }
                if (data.status == 2){
                    $("#notice").show();
                    $("#notice").text("未登录或登录已过期，请重新登录");
                    $("#imgWait").hide();
                }
                if (data.status == 1009){
                    $("#notice").show();
                    $("#notice").text("文件已存在");
                    $("#imgWait").hide();
                }
            }
        })
    });
});
