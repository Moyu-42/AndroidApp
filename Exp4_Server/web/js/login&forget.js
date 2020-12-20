$(function opt() {
    jQuery("#btn-login").on('click', function (event) {
        if (user_check().form()) {
            jQuery.ajax({
                type: "post",
                url: "loginServlet",
                data: {
                    username: $("#username_user").val(),
                    password: $("#password_user").val()
                },
                dataType: "json",
                success: function (data) {
                    alert(data["Result"]);
                    if (data["Result"] == "登录成功!") {
                        window.location.href="getInfoServlet?username=" + $("#username_user").val();
                    }
                }
            });
        }
        return false;
    });
    jQuery('#btn-change_forget').on('click', function (event) {
        var flag = new Boolean();
        flag = confirm("确定修改密码?");
        if (flag) {
            if (forget_password().form()) {
                jQuery.ajax({
                    type: "post",
                    url: "changePasswdServlet",
                    data: {
                        username: $("#username_forget").val(),
                        password: $("#password_new").val()
                    },
                    dataType: "json",
                    success: function (data) {
                        alert(data["Result"]);
                        if (data["Result"] == "修改成功!") {
                            window.location.href="./index.jsp";
                        }
                    }
                })
            }
        }
        return false;
    })
});