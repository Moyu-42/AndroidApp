$(function opt() {
    jQuery("#btn-delete").on('click', function (event) {
        var flag = new Boolean();
        flag = confirm("确定要删除? 该操作不可逆");
        if (flag) {
            jQuery.ajax({
                type: "post",
                url: "deleteServlet",
                data: {
                    username: ""
                },
                dataType: "json",
                success: function (data) {
                    alert(data["Result"]);
                    if (data["Result"] == "注销成功!") {
                        window.location.href="./index.jsp";
                    }
                }
            });
        }
        return false;
    });
});