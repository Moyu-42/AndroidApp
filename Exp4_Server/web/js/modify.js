$(function opt() {
    jQuery("#btn-update").on('click', function (event) {
        if (modify_check().form()) {
            var flag_conf = new Boolean();
            flag_conf = confirm("确定要修改?");
            if (flag_conf) {
                jQuery.ajax({
                    type: "post",
                    url: "modifyInfoServlet",
                    data: {
                        name: $("#name_modify").val(),
                        teleno: $("#teleno_modify").val(),
                        age: $("#age_modify").val()
                    },
                    dataType: "json",
                    success: function (data) {
                        alert(data["Result"]);
                        if (data["Result"] == "修改信息成功!") {
                            window.location.href="./login_info.jsp";
                        }
                    }
                });
            }
        }
        return false;
    });
    jQuery("#btn-change").on('click', function (event) {
        if (change_password().form()) {
            var flag_conf = new Boolean();
            flag_conf = confirm("确定要修改?");
            if (flag_conf) {
                jQuery.ajax({
                    type: "post",
                    url: "changePasswdServlet",
                    data: {
                        username: "",
                        password: $("#password_new_change").val()
                    },
                    dataType: "json",
                    success: function (data) {
                        alert(data["Result"]);
                        if (data["Result"] == "修改成功!") {
                            window.location.href="./login_info.jsp";
                        }
                    }
                });
            }
        }
        return false;
    });
});