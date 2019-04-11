/*
* 登陆页面js
* */
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
            layer.msg("请输入用户名！",{
                icon: 2,
                offset: '30%',
                time: 2000});
            return false;
        }
        if (password === "") {
            layer.msg("请输入密码！",{
                icon: 2,
                offset: '30%',
                time: 2000});
            return false;
        }
        if (validateCode === "") {
            layer.msg("请输入验证码！",{
                icon: 2,
                offset: '30%',
                time: 2000});
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
                    layer.msg(data.msg,{
                        icon: 1,
                        offset: '30%',
                        time: 1000
                    },function () {
                        location.href = ctx + "index";
                    });
                } else {
                    layer.msg(data.msg, {
                        icon: 2,
                        offset: '30%',
                        time: 2000
                    });
                    var url = ctx + "kaptcha/render?data=" +Math.random();
                    $("#imgCode").attr("src",url);
                }
            }
        });
    });

    $(document).keyup(function (event) {
        if (event.keyCode == 13) {
            $("#login-btn").click();
        }
    });
});