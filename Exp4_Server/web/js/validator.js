function user_check() {
    return $('#form-users').validate({
        errorElement : 'span',
        errorClass : 'help-block',
        rules: {
            username_user: {
                required: true,
                maxlength: 10
            },
            password_user: {
                required: true,
                maxlength: 8
            }
        },
        messages: {
            username_user: {
                required: "请输入用户名",
                maxlength: "最大长度为10"
            },
            password_user: {
                required: "请输入密码",
                maxlength: "最大长度为8"
            }
        },
        //自定义错误消息放到哪里
        errorPlacement : function(error, element) {
            element.next().remove();//删除显示图标
            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
            element.closest('.form-group').append(error);//显示错误消息提示
        },
        //给未通过验证的元素进行处理
        highlight : function(element) {
            $(element).closest('.form-group').addClass('has-error has-feedback');
        },
        //验证通过的处理
        success : function(label) {
            var el=label.closest('.form-group').find("input");
            el.next().remove();//与errorPlacement相似
            el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
            label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
            label.remove();
        },
    })
}
function modify_check() {
    return $('#form-modify').validate({
        errorElement : 'span',
        errorClass : 'help-block',
        rules: {
            name_modify: {
                maxlength: 20
            },
            age_modify: {
                digits: true,
                max: 800,
                min: 0
            },
            teleno_modify: {
                digits: true,
                maxlength: 11,
                minlength: 11
            }
        },
        messages: {
            name_modify: {
                maxlength: "最大长度为20"
            },
            age_modify: {
                digits: "年龄只能为数字",
                max: "不能超过800",
                min: "不能低于0"
            },
            teleno_modify: {
                digits: "电话号码只能为数字",
                maxlength: "长度只能为11位",
                minlength: "长度只能为11位"
            }
        },
        //自定义错误消息放到哪里
        errorPlacement : function(error, element) {
            element.next().remove();//删除显示图标
            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
            element.closest('.form-group').append(error);//显示错误消息提示
        },
        //给未通过验证的元素进行处理
        highlight : function(element) {
            $(element).closest('.form-group').addClass('has-error has-feedback');
        },
        //验证通过的处理
        success : function(label) {
            var el=label.closest('.form-group').find("input");
            el.next().remove();//与errorPlacement相似
            el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
            label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
            label.remove();
        },
    });
}
function forget_password() {
    return $('#form-forget').validate({
        errorElement : 'span',
        errorClass : 'help-block',
        rules: {
            username_forget: {
                required: true,
                maxlength: 10
            },
            password_new: {
                required: true,
                maxlength: 8
            },
            password_confirm: {
                required: true,
                maxlength: 8,
                equalTo: "#password_new"
            }
        },
        messages: {
            username_forget: {
                required: "请输入用户名",
                maxlength: "最大长度为10"
            },
            password_new: {
                required: "请输入密码",
                maxlength: "最大长度为8",
            },
            password_confirm: {
                required: "请再次输入密码",
                equalTo: "必须与输入的密码相同",
                maxlength: "最大长度为8"
            }
        },
        //自定义错误消息放到哪里
        errorPlacement : function(error, element) {
            element.next().remove();//删除显示图标
            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
            element.closest('.form-group').append(error);//显示错误消息提示
        },
        //给未通过验证的元素进行处理
        highlight : function(element) {
            $(element).closest('.form-group').addClass('has-error has-feedback');
        },
        //验证通过的处理
        success : function(label) {
            var el=label.closest('.form-group').find("input");
            el.next().remove();//与errorPlacement相似
            el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
            label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
            label.remove();
        },
    })
}
function change_password() {
    return $('#form-change').validate({
        errorElement : 'span',
        errorClass : 'help-block',
        rules: {
            password_new_change: {
                required: true,
                maxlength: 8
            },
            password_confirm_change: {
                required: true,
                maxlength: 8,
                equalTo: "#password_new_change"
            }
        },
        messages: {
            password_new_change: {
                required: "请输入密码",
                maxlength: "最大长度为8"
            },
            password_confirm_change: {
                required: "请再次输入密码",
                equalTo: "必须与输入的密码相同",
                maxlength: "最大长度为8"
            }
        },
        //自定义错误消息放到哪里
        errorPlacement : function(error, element) {
            element.next().remove();//删除显示图标
            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
            element.closest('.form-group').append(error);//显示错误消息提示
        },
        //给未通过验证的元素进行处理
        highlight : function(element) {
            $(element).closest('.form-group').addClass('has-error has-feedback');
        },
        //验证通过的处理
        success : function(label) {
            var el=label.closest('.form-group').find("input");
            el.next().remove();//与errorPlacement相似
            el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
            label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
            label.remove();
        },
    })
}
function register() {
    return $('#form-register').validate({
        errorElement : 'span',
        errorClass : 'help-block',
        rules: {
            username_register: {
                required: true,
                maxlength: 10
            },
            password_register: {
                required: true,
                maxlength: 8
            },
            password_confirm_register: {
                required: true,
                maxlength: 8,
                equalTo: "#password_register"
            },
            name_register: {
                required: true,
                maxlength: 20
            },
            age_register: {
                digits: true,
                max: 800,
                min: 0
            },
            teleno_register: {
                digits: true,
                maxlength: 11,
                minlength: 11
            }
        },
        messages: {
            username_register: {
              required: "请输入用户名",
              maxlength: "最大长度为10"
            },
            password_register: {
                required: "请输入密码",
                maxlength: "最大长度为8"
            },
            password_confirm_register: {
                required: "请再次输入密码",
                equalTo: "必须与输入的密码相同",
                maxlength: "最大长度为8"
            },
            name_register: {
                required: "请输入名称",
                maxlength: "最大长度为20"
            },
            age_register: {
                digits: "年龄只能为数字",
                max: "不能超过800",
                min: "不能低于0"
            },
            teleno_register: {
                digits: "电话号码只能为数字",
                maxlength: "长度只能为11位",
                minlength: "长度只能为11位"
            }
        },
        //自定义错误消息放到哪里
        errorPlacement : function(error, element) {
            element.next().remove();//删除显示图标
            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
            element.closest('.form-group').append(error);//显示错误消息提示
        },
        //给未通过验证的元素进行处理
        highlight : function(element) {
            $(element).closest('.form-group').addClass('has-error has-feedback');
        },
        //验证通过的处理
        success : function(label) {
            var el=label.closest('.form-group').find("input");
            el.next().remove();//与errorPlacement相似
            el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
            label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
            label.remove();
        },
    })
}