$(function () {
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%' /* optional */
    });

    $("#imgCode").click(function () {
        var url = ctx + "kaptcha/render?data=" +Math.random();
        $("#imgCode").attr("src",url)
    });
    $("#login-btn").click(function () {
        var username = $("input[name='username']").val().trim();
        var password = $("input[name='password']").val().trim();
        var validateCode = $("input[name='validateCode']").val().trim();
        var rememberMe = $("input[name='rememberMe']").is(':checked');
        if (username === "") {
            layer.alert("请输入用户名！",{icon:5});
            return false;
        }
        if (password === "") {
            layer.alert("请输入密码！",{icon:5});
            return false;
        }
        if (validateCode === "") {
            layer.alert("请输入验证码！",{icon:5});
            return false;
        }
        $.ajax({
            url: ctx + "login",
            type: "post",
            data:{
                "username" : username,
                "password" : password,
                "validateCode" : validateCode,
                "rememberMe" : rememberMe
            },
            dataType: "json",
            success: function (data) {
                if (data.code === 1) {
                    location.href = ctx + "index";
                } else {
                    var url = ctx + "kaptcha/render?data=" +Math.random();
                    $("#imgCode").attr("src",url);
                    layer.alert(data.msg,{icon:5})
                }
            }
        });
    });
});