/*
 * 封装常用的js
 */
var Valor = (function () {
    var valor = {};
    /*提示框*/
    valor.errorMsg = function (content) {
        layer.msg(content,{
            icon: 2,
            offset: '30%',
            time: 2000
        });
    };
    valor.successMsg = function (content) {
        layer.msg(content,{
            icon: 1,
            offset: '30%',
            time: 2000
        });
    };
    /*询问框*/
    valor.confirm = function (content, d) {
        layer.confirm(content,{
            icon:3,
            title:"系统提示",
            btn: ['确认', '取消'],
        },function (index) {
            layer.close(index);
            d(true);
        });
    };
    /*ajax请求*/
    valor.sendAjax = function (url, data, d, type, contentType, async) {
        $.ajax({
            url : url,
            cache : false,
            async : async === undefined ? true : async,
            data : data,
            type : type === undefined ? 'post' : type,
            contentType : contentType === undefined ? 'application/x-www-form-urlencoded; charset=UTF-8' : contentType,
            success : function (data) {
                if (typeof d == "function") {
                    d(data);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (XMLHttpRequest.status == 403) {
                    layer.msg("您没有权限访问，请联系管理员！")
                } else if (XMLHttpRequest.status == 500) {
                    layer.msg("服务器内部错误！")
                } else {
                    layer.msg("服务器未知错误！")
                }
            }
        });
    };
    return valor;
})(Valor,window);