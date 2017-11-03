$(document).ready(function () {
    get_file_list();
    get_user_info();
    $(function () {
        $('#commit_name').click(
            function () {
                var user_name = document.getElementById("user_name").value;
                $("#order_user_name").text(user_name);
                $("#not_commit_name").click();
            }
        );
        $('#commit_phone').click(
            function () {
                var phone_num = document.getElementById("phone_num").value;
                $("#order_phone_num").text(phone_num);
                $("#not_commit_phone").click();
            }
        );
    });

    $(document).keyup(function (event) {
        if (event.keyCode == 13) {
            $("#register_button").trigger("click");
        }
    });
})

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
            var file_num = file_names.length;
            if (file_num == 1) {
                $("#order_file_first").show();
                $("#order_file_second").hide();
                $("#order_file_third").hide();
                $("#order_first_file_name").text(file_names[0]);
            }
            if (file_num == 2) {
                $("#order_file_first").show();
                $("#order_file_second").show();
                $("#order_file_third").hide();
                $("#order_first_file_name").text(file_names[0]);
                $("#order_second_file_name").text(file_names[1]);
            }
            if (file_num == 3) {
                $("#order_file_first").show();
                $("#order_file_second").show();
                $("#order_file_third").show();
                $("#order_first_file_name").text(file_names[0]);
                $("#order_second_file_name").text(file_names[1]);
                $("#order_third_file_name").text(file_names[2]);
            }
        }
    })
}

function get_user_info() {
    $.ajax({
        url: '/order/getUserInfo',
        type: 'get',
        cache: false,
        processData: false,
        contentType: false,
        success: function (result) {
            $("#order_user_name").text(result.userName);
            $("#order_phone_num").text(result.phoneNum);
        }

    })

}

