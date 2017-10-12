$(function () {
    $("#documentSubmit").click(function () {
        $("#notice").hide();
        $("#success").hide();
        var formData = new FormData($('#document-form')[0]);
        if ($('#documentInputFile').val() == "") {
            $("#notice").show();
            $("#notice").text("请选择文件");
            return false;
        }
        ;
        $("#imgWait").show();
        $.ajax({
            url: 'file/upload',
            type: 'post',
            cache: false,
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data.status == 1) {
                    $("#success").show();
                    $("#success").text("文件上传成功");
                    $("#imgWait").hide();
                }
                if (data.status == 1008) {
                    $("#notice").show();
                    $("#notice").text("请选择文件");
                    $("#imgWait").hide();
                }
                if (data.status == 2) {
                    $("#notice").show();
                    $("#notice").text("未登录或登录已过期，请重新登录");
                    $("#imgWait").hide();
                }
                if (data.status == 1009) {
                    $("#notice").show();
                    $("#notice").text("文件已存在");
                    $("#imgWait").hide();
                }
            }
        })
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
})
$('#typeSelectImg').click(function () {
    $("#notice").hide();
    $("#success").hide();
    $("#typeHelp").text("暂支持文件类型：.jpg .png ");
})
