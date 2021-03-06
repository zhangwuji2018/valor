/*
 * 初始化首页js
 */
$(function () {
    var menu = '';
    // 根节点主键(一级菜单)
    var rootId = 0;
    $.ajax({
        url:ctx + 'getMenu',
        type:'post',
        data:{},
        success: function (data) {
            getMenuData(rootId, data);
            $("#sidebar").append(menu);
        }
    });
    // 根据菜单的主键id生成菜单列表对应的html
    // array对应的菜单信息
    function getMenuData(id, menuArray) {
        var childMenuArray = getChildMenu(id, menuArray);
        if (childMenuArray.length > 0) {
            if (menu === '') {
                menu += '<ul class="sidebar-menu tree" data-widget="tree">';
            } else {
                menu += '<ul class="treeview-menu">';
            }
            for (var i in childMenuArray) {
                // 根据当前菜单是否有子菜单，添加不同的样式
                var array = getChildMenu(childMenuArray[i].id, menuArray);
                if (array.length > 0) {
                    menu += '<li class="treeview"><a href="#"><i class="'+childMenuArray[i].icon+'"></i>' +
                        '<span>'+childMenuArray[i].name+'</span>' +
                        '<span class="pull-right-container">' +
                        '<i class="fa fa-angle-left pull-right"></i></span> </a>';
                    getMenuData(childMenuArray[i].id, menuArray);
                } else {
                    menu += '<li><a href="'+ctx+childMenuArray[i].url.substring(1,childMenuArray[i].url.length)+'">' +
                        '<i class="'+childMenuArray[i].icon+'"></i> <span>'+childMenuArray[i].name+'</span></a>';
                }
                menu += '</li>';
            }
            menu += '</ul>';
        }
    }

    // 根据主键获取id对应的子菜单数组信息
    function getChildMenu(id, menuArray) {
        var array = [];
        for(var i in menuArray) {
            if (id === menuArray[i].parentId) {
                array.push(menuArray[i]);
            }
        }
        return array;
    }
    // 退出
    $("#loginout-btn").click(function () {
        Valor.confirm("确认退出！！！",function () {
            Valor.sendAjax(ctx + "logout",{},function (data) {
                if (data.code === 1) {
                    window.location.href = ctx +"login";
                } else {
                    Valor.errorMsg(data.msg);
                }
            })
        })
    });
});