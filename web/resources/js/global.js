$(document).ready(function () {
    $.ajax({
        type: "post",
        url: "/checkLogin",
        dataType: "json",
        contentType: "text/plain",
        success: function (result) {
            if (result.status === 1) {
                $("#loginCoin").hide();
                $("#registerCoin").hide();
                $("#logoutCoin").show();
            } else {

            }
        }
    });
});