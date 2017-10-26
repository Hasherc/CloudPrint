$("#delete_file_first").click(function () {
    var file_name = $("#file_first").text();
    delete_file(file_name);
});
$("#delete_file_second").click(function () {
    var file_name = $("#file_second").text();
    delete_file(file_name)
});
$("#delete_file_third").click(function () {
    var file_name = $("#file_third").text();
    delete_file(file_name);
});
$("#typeSelectFile").click(function () {
    check_file_num();
    get_file_list();
});
$("#typeSelectImg").click(function () {
    check_file_num();
    get_file_list();
});
function delete_file(file_name) {
    $.ajax({
        url: '/file/deleteFile',
        type: 'post',
        data: file_name,
        cache: false,
        processData: false,
        contentType: false,
        success: function (result) {
            check_file_num();
            get_file_list();
            if (result.status == 1){
                $("#success").show();
                $("#success").text("文件（ " +file_name + " ）删除成功");
            }
            if (result.status == 0){

            }
            if (result.status == 2){
                alert("登录已失效请刷新页面重新登录");
            }

        }
    })
}
function get_file_list() {
    $.ajax({
        url: '/file/fileList',
        type: 'get',
        cache: false,
        processData: false,
        contentType: false,
        success: function (result) {
            var file_names = new Array();
            file_names = result.fileNames;
            if (file_num == 0){
                $("#first_file_block").hide();
                $("#second_file_block").hide();
                $("#third_file_block").hide();
            }
            if (file_num == 1){
                $("#first_file_block").show();
                $("#file_first").text(file_names[0]);
                $("#second_file_block").hide();
                $("#third_file_block").hide();
            }
            if (file_num >= 2){
                $("#first_file_block").show();
                $("#file_first").text(file_names[0]);
                $("#second_file_block").show();
                $("#file_second").text(file_names[1]);

                $("#third_file_block").hide();
            }
            if (file_num == 3){
                $("#first_file_block").show();
                $("#file_first").text(file_names[0]);
                $("#second_file_block").show();
                $("#file_second").text(file_names[1]);
                $("#third_file_block").show();
                $("#file_third").text(file_names[2]);
            }
        }
    })
}
function check_file_num() {
    $.ajax({
        url: '/file/fileNum',
        type: 'get',
        cache: false,
        processData: false,
        contentType: false,
        async:false,
        success:function (result) {
            file_num = result.fileNum;
            if (file_num > 0){
                $("#toOrder").show();
                if (file_num == 3){
                    $("#warning").show();
                    $("#documentSubmit").hide();
                    document.getElementById("warning").innerHTML = "每个订单最多打印3个文件</br>目前已经上传了3个文件了哦";
                }else {
                    $("#warning").hide();
                    $("#documentSubmit").show();
                }
            }else {
                $("#toOrder").hide();
            }

        }
    })
}

$("#documentSubmit").click(function () {
        $("#notice").hide();
        $("#success").hide();
        var formData = new FormData($('#document-form')[0]);
        if ($('#documentInputFile').val() == "") {
            $("#notice").show();
            $("#notice").text("请选择文件");
            return false;
        }
        $("#imgWait").show();
        $.ajax({
            url: 'file/upload',
            type: 'post',
            cache: false,
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {

                check_file_num();
                get_file_list();
                $("#documentInputFile").val("");
                if (data.status == 1) {
                    $("#success").show();
                    document.getElementById("success").innerHTML="<strong>文件" + data.fileName + "上传成功</strong><br><br>您可以继续上传或点击结算按钮进入结算页面";
                    $("#imgWait").hide();
                }
                else if (data.status == 1008) {
                    $("#notice").show();
                    $("#notice").text("请选择文件");
                    $("#imgWait").hide();
                }
                else if (data.status == 2) {
                    $("#notice").show();
                    $("#notice").text("未登录或登录已过期，请重新登录");
                    setTimeout("window.location.href = '/toLogin'", 1000);
                    $("#imgWait").hide();
                }
                else if (data.status == 1009) {
                    $("#notice").show();
                    $("#notice").text("文件已存在");
                    $("#imgWait").hide();
                }
                else if (data.status == 1006) {
                    $("#notice").show();
                    $("#notice").text("文件上传失败");
                    $("#imgWait").hide();
                }
                else if (data.status == 1011) {
                    $("#success").show();
                    $("#success").text("文件上传成功");

                    $("#imgWait").hide();
                }else {
                    $("#notice").show();
                    $("#notice").text("文件上传出错");
                    $("#imgWait").hide();
                }

            }
        });
    });
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
});

$('#typeSelectImg').click(function () {
    $("#notice").hide();
    $("#success").hide();
    $("#typeHelp").text("暂支持文件类型：.jpg .png ");
});
