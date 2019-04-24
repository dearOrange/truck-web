layui.config({
    base: '../../static/layui/my/modules/' //存放拓展模块的根目录
}).extend({ //设定模块别名
    sysFunction: 'functions'//如果 tabTab.js 是在根目录，也可以不用设定别名
    ,treeGrid:'treeGrid'
    ,xtree:'layui-xtree'
});
//JavaScript代码区域
layui.use(['element', 'jquery', 'layer', 'sysFunction'], function () {
    var element = layui.element,
        $ = layui.jquery,
        sysFunction = layui.sysFunction;

    $(function () {
        var leftmenu = sysFunction.initMenu();
        if(leftmenu!=undefined && leftmenu!=''){
            $(".layui-side-scroll").html(leftmenu);
            element.init();  //初始化页面元素
            //初始内容页
        }

        sysFunction.tabFunc(element, "首页", "/welcome");
        //延时加载
        setTimeout(function () {
            if (sessionStorage.getItem("menu")) {
                menu = JSON.parse(sessionStorage.getItem("menu"));
                for (var i = 0; i < menu.length; i++) {
                    sysFunction.tabFunc(element, menu[i].id, menu[i].url);
                }
            } else {
                return false;
            }
            if (sessionStorage.getItem("curMenu")) {
                $('.layui-tab-title').find('layui-this').removeClass('layui-class');
                curMenu = JSON.parse(sessionStorage.getItem("curMenu"));
                id = curMenu.id;
                if (id) { //因为默认桌面首页不存在lay-id,所以要对此判断
                    $('.layui-tab-title li[lay-id="' + id + '"]').addClass('layui-this');
                    tab.tabChange(id);
                } else {
                    sysFunction.tabFunc(element, "首页", "/welcome");
                }
            } else {
                sysFunction.tabFunc(element, "首页", "/welcome");
            }
        }, 100);

        $.ajaxSetup({});
        //初始化加载结束
    });


    /**
     * @todo 监听layui Tab项的关闭按钮，改变本地存储
     */
    element.on('tabDelete(nav-filter)', function (data) {
        var layId = $(this).parent('li').attr('lay-id');
        //console.log(layId);
        sysFunction.removeStorageMenu(layId);
    });

    /*
    * @todo tab触发事件：增加、删除、切换
    */
    var tab = {
        tabDelete: function (id) {
            element.tabDelete("nav-filter", id); //删除
            removeStorageMenu(id);
        },
        tabChange: function (id) {
            //切换到指定Tab项
            element.tabChange('nav-filter', id);
        },
        tabDeleteAll: function (ids) { //删除所有
            $.each(ids, function (i, item) {
                element.tabDelete("nav-filter", item);
            })
            sessionStorage.removeItem('menu');
        }
    };

    $("#loginOut").click(function () {
        layer.confirm('您确定要退出系统吗？', {icon: 7, title: '退出系统'}, function (index) {
            $.ajax({
                url: '/doLogout',
                type: 'POST',
                dataType: "json",
                success: function (result) {
                    if (result.code == 200 && result.data) {
                        sessionStorage.setItem("token", '');
                        location.href = '/';
                    } else {
                        layer.msg(result.msg);
                        sessionStorage.setItem("token", '');
                    }
                }
            });
        });
    });

    $(".opt-newTab").click(function () {
        if ($(this).attr('url') == undefined)
            layer.msg($(this).text() + "仍在开发中");
        else {
            if ($(this).attr('url') == '/ex404') {
                layer.msg($(this).text() + "仍在开发中");
            }
            sysFunction.tabFunc(element, $(this).text(), $(this).attr('url'));
        }
    });


    $('#fullscreen').on('click', function () {
        var docElm = document.documentElement;
        if (docElm.requestFullscreen) {       //W3C
            docElm.requestFullscreen();
        } else if (docElm.mozRequestFullScreen) { //FireFox
            docElm.mozRequestFullScreen();
        } else if (docElm.webkitRequestFullScreen) { //Chrome等
            docElm.webkitRequestFullScreen();
        } else if (docElm.msRequestFullscreen) {//IE11
            docElm.msRequestFullscreen();
        }
        layer.msg('按Esc即可退出全屏');
    });

    /*
    * @todo 左侧导航菜单的显示和隐藏
    */
    $('.left_open .iconfont').click(function (event) {
        if ($('.layui-side').css('left') == '0px') {
            $('.layui-side').animate({
                left: '-200px'
            }, 100);
            $('.layui-body').animate({
                left: '0px'
            }, 100);
            $('.layui-footer').animate({
                left: '0px'
            }, 100);
            // $('.page-content-bg').hide();
        } else {
            $('.layui-side').animate({
                left: '0px'
            }, 100);
            $('.layui-body').animate({
                left: '200px'
            }, 100);
            $('.layui-footer').animate({
                left: '200px'
            }, 100);
            // if($(window).width() < 768) {
            // 	$('.page-content-bg').show();
            // }
        }
    });

});
