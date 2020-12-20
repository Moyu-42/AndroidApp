$(function opt() {
    jQuery("#btn-register").on('click', function (event) {
        if (register().form()) {
            var flag_conf = new Boolean();
            flag_conf = confirm("确定要注册?");
            if (flag_conf) {
                jQuery.ajax({
                    type: "post",
                    url: "registerServlet",
                    data: {
                        username: $("#username_register").val(),
                        password: $("#password_register").val(),
                        name: $("#name_register").val(),
                        teleno: $("#teleno_register").val(),
                        age: $("#age_register").val()
                    },
                    dataType: "json",
                    success: function (data) {
                        alert(data["Result"]);
                        if (data["Result"] == "注册成功!") {
                            window.location.href="./index.jsp";
                        }
                    }
                });
            }
        }
        return false;
    });
});