//layui模块的定义
layui.define(['jquery', 'form', 'layer'], function (exports) {
    var $ = layui.jquery,
        form = layui.form,
        layer = layui.layer;

    var sysFunction = {
        tabFunc: function (element, funcName, tabContent) {
            if (funcName != undefined) {
                // layer.msg
                var switchFlag = false;
                $("#tab-title li").each(function () {
                    $("this").remove();
                    var tabName = $(this).attr("lay-id");
                    if (tabName != undefined) {
                        if (tabName.trim() == funcName.trim()) {
                            switchFlag = true;
                            element.tabChange('nav-filter', $(this).attr("lay-id"));
                        }
                    }
                })
                if (!switchFlag) {
                    var content = $.ajax({url: tabContent, async: false}).responseText;
                    element.tabAdd('nav-filter', {
                        title: funcName
                        , content: content //支持传入html
                        , id: funcName
                    });
                    element.tabChange('nav-filter', funcName);
                    //当前窗口内容
                    sysFunction.setStorageMenu(funcName, tabContent, funcName);
                    sysFunction.setStorageCurMenu(funcName, tabContent, funcName);
                }
            }
        },
        /**
         *@todo 本地存储 localStorage
         * 为了保持统一，将sessionStorage更换为存储周期更长的localStorage
         */
        setStorageMenu: function (title, url, id) {//本地存储记录所有打开的窗口
            var menu = JSON.parse(sessionStorage.getItem('menu'));
            if (menu) {
                var deep = false;
                for (var i = 0; i < menu.length; i++) {
                    if (menu[i].id == id) {
                        deep = true;
                        menu[i].title = title;
                        menu[i].url = url;
                        menu[i].id = id;
                    }
                }
                if (!deep) {
                    menu.push({
                        title: title,
                        url: url,
                        id: id
                    })
                }
            } else {
                var menu = [{
                    title: title,
                    url: url,
                    id: id
                }]
            }
            sessionStorage.setItem('menu', JSON.stringify(menu));
        },
        getStorageMenu: function (id) {//本地存储记录所有打开的窗口
            var menu = JSON.parse(sessionStorage.getItem('menu'));
            if (menu) {
                for (var i = 0; i < menu.length; i++) {
                    if (menu[i].id == id) {
                        return menu[i].url;
                    }
                }
            }
            return "";
        },
        setStorageCurMenu: function (text, url, id) { //本地存储记录当前打开窗口
            var curMenu = {
                title: text,
                url: url,
                id: id
            }
            sessionStorage.setItem('curMenu', JSON.stringify(curMenu));
        },
        //本地存储中移除删除的元素
        removeStorageMenu: function (id) {
            var menu = JSON.parse(sessionStorage.getItem('menu'));
            //var curMenu = JSON.parse(localStorage.getItem('curMenu'));
            if (menu) {
                var deep = false;
                for (var i = 0; i < menu.length; i++) {
                    if (menu[i].id == id) {
                        deep = true;
                        menu.splice(i, 1);
                    }
                }
            } else {
                return false;
            }
            sessionStorage.setItem('menu', JSON.stringify(menu));
        },
        initMenu: function () {
            if (sessionStorage != undefined && sessionStorage != null) {
                var user = sessionStorage.getItem("token");
                if (user != undefined && null != user && '' != user) {
                    var info = JSON.parse(user),count = 0;
                    if (null != info && null != info.functions) {
                        var html = '<ul class="layui-nav layui-nav-tree"  lay-filter="test">';
                        info.functions.forEach(function (item) {
                            html += '<li class="layui-nav-item ';
                            if(count === 0 ) {
                                html += 'layui-nav-itemed';
                                count ++;
                            }
                            html +='"><a href="javascript:;">';
                            if(item.icon!=undefined){
                                html +='<i class="iconfont">';
                                    html += item.icon;
                                html +='</i>';
                            }
                            html +='<cite>';
                            html += item.name
                            html += '</cite></a>';
                            if (null != item.childs  && item.childs.length>0) {
                                html += '<dl class="layui-nav-child">';
                                item.childs.forEach(function (d) {
                                    html += '<dd class="opt-newTab" url ="';
                                    html += d.href
                                    html += '"><a href="javascript:;">';
                                    if(d.icon!=undefined){
                                        html +='<i class="iconfont">';
                                        html += d.icon;
                                        html +='</i>';
                                    }
                                    html += d.name
                                    html += '</a></dd>';
                                });
                                html += '</dl>';
                            }
                            html += '</li>';
                        });
                        html += '</ul>';
                        return html;
                    }
                    return '';
                } else {
                    location.href = "/";
                }
            } else {
                location.href = "/";
            }
        }
    }

    /*
 * @todo 弹出层，弹窗方法
 * layui.use 加载layui.define 定义的模块，当外部 js 或 onclick调用 use 内部函数时，需要在 use 中定义 window 函数供外部引用
 * http://blog.csdn.net/xcmonline/article/details/75647144
 */
    /*
        参数解释：
        title   标题
        url     请求的url
        id      需要操作的数据id
        w       弹出层宽度（缺省调默认值）
        h       弹出层高度（缺省调默认值）
    */
    window.myformShow = function (title, url, w, h) {
        if (title == null || title == '') {
            title = false;
        }
        if (url == null || url == '') {
            layer.msg("404:没有请求的页面");
            return;
        }
        if (w == null || w == '') {
            w = ($(window).width() * 0.9);
        }

        if (h == null || h == '') {
            h = ($(window).height() - 50);
        }
        layer.open({
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false, //不固定
            maxmin: true,
            shadeClose: true,
            shade: 0.4,
            title: title,
            content: url
        });
    }
    /*弹出层+传递ID参数*/
    window.myformEdit = function (title, url, val, tip, w, h) {
        if (title == null || title == '') {
            title = false;
        }
        if (url == null || url == '') {
            layer.msg("404:没有请求的页面");
            return;
        }
        if (w == null || w == '') {
            w = ($(window).width() * 0.9);
        }
        if (h == null || h == '') {
            h = ($(window).height() - 50);
        }
        layer.open({
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false, //不固定
            maxmin: true,
            shadeClose: true,
            shade: 0.4,
            title: title,
            content: url,
            success: function (layero, index) {
                //向iframe页的id=house的元素传值  // 参考 https://yq.aliyun.com/ziliao/133150
                var body = layer.getChildFrame('body', index);
                if (tip.id != undefined) {
                    body.contents().find("#" + tip.id).val(val.id);
                }
                if (tip.code != undefined) {
                    body.contents().find("#" + tip.code).val(val.code);
                }
            },
            error: function (layero, index) {
                layer.msg("出错了");
            }
        });
    }

    exports('sysFunction', sysFunction);
});
