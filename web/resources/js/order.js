$(document).ready(function () {
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
    $(function () {

    })
    $(document).keyup(function (event) {
        if (event.keyCode == 13) {
            $("#register_button").trigger("click");
        }
    });
})


