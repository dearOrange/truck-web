layui.use(['jquery', 'layer', 'table', 'laydate'], function () {
    var token;
    layui.$.ajaxSetup({
        beforeSend(xhr) {
            if (sessionStorage != undefined && sessionStorage != null && sessionStorage.getItem("token") != undefined
                && sessionStorage.getItem("token") != null && sessionStorage.getItem("token") != '') {
                if ("" != JSON.parse(sessionStorage.getItem("token")).token) {
                    token = JSON.parse(sessionStorage.getItem("token")).token;
                    xhr.setRequestHeader('Authorization', token);
                }
            }
        },
        complete(xhr, status) {
            if (xhr.responseJSON != undefined && (xhr.responseJSON.code == 401 || xhr.responseJSON.code == 403)) {
                // console.log(xhr.responseJSON.code+"----------------"+token);
                if (sessionStorage != undefined && sessionStorage != null)
                    sessionStorage.setItem("token", '');
                location.href = "/";
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            switch (jqXHR.status) {
                case (200):
                    break;
                case (500):
                    layer.alert('服务器系统内部错误', {
                        icon: 2
                    });
                    break;
                case (401):
                    layer.alert('未登录', {
                        icon: 2
                    });
                    location.href = "/";
                    break;
                case (403):
                    layer.alert('无权限执行此操作', {
                        icon: 2
                    });
                    break;
                case (408):
                    layer.alert('请求超时', {
                        icon: 2
                    });
                    break;
                default:
                    layer.alert('内容不存在,请联系管理员', {
                        icon: 2
                    });
            }
        },
        cache: false
    });

    initFunction = {
        getDictSelectList: function (b,form, dictType, tip, val) {
            // 请求
            layui.$.ajax({
                type: 'GET',
                url: '/dict/getListByType?type=' + dictType,
                dataType: "json",
                success: function (result) {
                    if (result.code == 200 && result.data != null) {
                        var data = result.data, html ;
                        if(b){
                           html = '<option value="">请选择</option>'
                        }
                        layui.$('#' + tip).empty();
                        data.forEach(function (item) {
                            html += ' <option value="' + item.dictCode + '"';
                            if (val != undefined && item.dictCode == val) {
                                html += '  selected="selected" ';
                            }
                            html += '>' + item.dictValue + '</option>';
                        });
                        layui.$('#' + tip).append(html);
                        form.render('select');
                    } else {
                        layer.msg(result.msg);
                    }
                }
            })
        },
        getSelectAreaTree: function (b,form, tip, param, val, flag) {
            if (param == undefined) {
                param = {};
            }
            // 请求
            layui.$.ajax({
                type: 'GET',
                url: '/area/getTreeList',
                data: param,
                dataType: "json",
                success: function (result) {
                    if (result.code == 200 && result.data != null) {
                        var data = result.data, html ='';
                        if(b){
                            html += '<option value="">请选择</option>'
                        }
                        layui.$('#' + tip).empty();
                        data.forEach(function (item) {
                            html += ' <option value="' + item.id + '" data-level="' + item.level + '"';
                            if (flag == undefined || flag) {
                                if (val != undefined && item.id == val) {
                                    html += ' selected="selected" ';
                                }
                            } else {
                                if (val != undefined && item.name == val) {
                                    html += ' selected="selected" ';
                                }
                            }
                            html += '>';
                            if (item.level > 1) {
                                for (var i = 1; i < item.level; i++) {
                                    html += "　　";　//js中连续显示多个空格，需要使用全角的空格
                                }

                            }
                            html += "├　" + item.name + '</option>';
                        });
                        layui.$('#' + tip).append(html);
                        form.render('select');
                    } else {
                        layer.msg(result.msg);
                    }
                }
            })
        },
        getSelectFunctionTree: function (b,form, tip, val) {//增加时，层级问题没有解决
            // 请求
            layui.$.ajax({
                type: 'GET',
                url: '/function/getTreeList',
                dataType: "json",
                success: function (result) {
                    if (result.code == 200 && result.data != null) {
                        var data = result.data,  html ;
                        if(b){
                            html = '<option value="">请选择</option>'
                        }
                        layui.$('#' + tip).empty();
                        data.forEach(function (item) {
                            html += ' <option value="' + item.id + '" data-level="' + item.parentId + '"';
                            if (val != undefined && item.id == val) {
                                html += ' selected="true" ';
                            }
                            html += '>';
                            if (item.parentId != null) {
                                html += "　　";　//js中连续显示多个空格，需要使用全角的空格
                            }
                            html += "├　" + item.name + '</option>';
                        });
                        layui.$('#' + tip).append(html);
                        form.render('select');
                    } else {
                        layer.msg(result.msg);
                    }
                }
            })
        },
        reloadTableList: function (param, url, tab, type) {
            //这里以搜索为例
            layui.table.reload(tab, {
                where: param
                , url: url//后台做模糊搜索接口路径
                , method: type
                , page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        },
        getMutlSelect: function (form, tip, url, param) {//增加时，层级问题没有解决
            // 请求
            layui.$.ajax({
                type: 'GET',
                url: url,
                data: param,
                dataType: "json",
                success: function (result) {
                    if (result.code == 200 && result.data != null) {
                        // var data = result.data, html = '<option value=""></option>';
                        // $('#' + tip).empty();
                        // data.forEach(function (item) {
                        //     html += ' <option value="' + item.value + '" ';
                        //     if (item.selected) {
                        //         html += ' selected="selected" ';
                        //     }
                        //     html += '>'+ item.name + '</option>';
                        // });
                        // $('#' + tip).append(html);
                        // form.render('select');
                        layui.formSelects.data(tip, 'local', {
                            arr: result.data
                        });
                    } else {
                        layer.msg(result.msg);
                    }
                }
            })
        },
        getSelectList: function (b,form, url, tip, val,type) {//暂有name和id字段的表使用
            // 请求
            layui.$.ajax({
                type: 'GET',
                url: url,
                dataType: "json",
                success: function (result) {
                    if (result.code == 200 && result.data != null) {
                        var data = result.data, html ;
                        if(b){
                            html = '<option value="">请选择</option>'
                        }
                        layui.$('#' + tip).empty();
                        data.forEach(function (item) {
                            html += ' <option value="' + item.id + '"';
                            if (val != undefined && item.id == val) {
                                html += '  selected="selected" ';
                            }
                            html += '>';
                            if(type!=undefined){
                                if( type=='car'){
                                    html += item.licensePlate;
                                }else if( type=='line'){
                                    html += item.licensePlate +'-'+ (item.departureDate==null?'':item.departureDate)+ ':' +item.startProvinceName+'_'+item.startCityName+'_'+item.startCountyName+'   -    '+item.endProvinceName+'_'+item.endCityName+'_'+item.endCountyName;
                                }else if(type == 'warehouseNeed'){
                                    html += item.goodsName;
                                }else if(type =='seeWarehouse'){
                                    html += item.dateDay;
                                }else if(type =='order'){
                                    html += item.orderCode;
                                }

                            }else {
                                html += item.name;
                            }
                            html+= '</option>';
                        });
                        layui.$('#' + tip).append(html);
                        form.render('select');
                    } else {
                        layer.msg(result.msg);
                    }
                }
            })
        },

        initTable: function (prefix, url, cols,toolbar) {
            var bar;
            if(toolbar == undefined){
                bar = 'tabletoolbar';
            }else{
                bar = toolbar;
            }
            //第一个实例
            layui.table.render({
                elem: '#' + prefix + 'Table'
                , height: 490
                , url: url + '/getList4Page' //数据接口
                , toolbar: '#'+ bar
                , even: true
                , cellMinWidth: 100
                , request: {
                    pageName: 'currentPage'
                    , limitName: 'pageSize'
                }
                , response: {
                    statusName: 'code' //数据状态的字段名称，默认：code
                    , statusCode: 200 //成功的状态码，默认：0
                    , msgName: 'msg' //状态信息的字段名称，默认：msg
                    , countName: 'count' //数据总数的字段名称，默认：count
                    , dataName: 'data' //数据列表的字段名称，默认：data
                }
                , page: true //开启分页
                , cols: cols
            });
        },
        tableToolBarOnEvent: function (prefix, url, title) {
            //头工具栏事件
            layui.table.on('toolbar(' + prefix + '_table_filter)', function (obj) {
                var checkStatus = layui.table.checkStatus(obj.config.id);
                switch (obj.event) {
                    case 'addButton':
                        myformShow('添加', url + '/add', 800, 600);
                        break;
                    case 'addCarButton':
                        myformShow('添加', url + '/caradd', 800, 600);
                        break;
                    case 'addGoodsButton':
                        myformShow('添加', url + '/goodsadd', 800, 600);
                        break;
                    case 'isDelAll':
                        var data = checkStatus.data;
                        var ids = "";
                        if (data.length > 0) {
                            data.forEach(function (item, index) {
                                ids += item.id + ",";
                            });
                            layer.confirm('您确定要批量删除' + title + '吗？', {
                                icon: 3,
                                title: title + '删除'
                            }, function (index) {
                                $.get(url + "/dels?ids=" + ids + "0", function (result) {
                                    if (result.code == 200) {
                                        layer.msg('批量删除成功', {
                                            icon: 1,
                                            time: 1200
                                        }, function () {
                                            initFunction.refreshList(prefix, url);
                                            //$(obj).parents("tr").remove();
                                        });
                                    } else {
                                        layer.msg(result.msg, {
                                            icon: 2,
                                            time: 1200
                                        }, function () {
                                            initFunction.refreshList(prefix, url);
                                        });
                                    }
                                })
                            });
                        } else {
                            layer.msg('请选择要删除的数据');
                        }
                        break;
                }
            });
        },

        refreshList: function (prefix, url) {
            var val = layui.$('#name').val();//获取输入框的值
            initFunction.reloadTableList({name: val}, url + '/getList4Page', prefix + 'Table', 'GET');
        },
        initLayDate: function (tip,format,type,range) {
            if(range == undefined){
                range = false;
            }
            if(type == undefined){
                type = 'date';
            }
            layui.laydate.render({
                elem: '#'+tip
                ,range: range
                ,type:type
                , format: format
            });
        },

        tableToolEvent: function (prefix, url, title) {
            //监听工具条
            layui.table.on('tool(' + prefix + '_table_filter)', function (obj) {
                //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                //获得当前行数据
                var data = obj.data, tip = {'id': 'id'}, layEvent = obj.event; //获得 lay-event 对应的值
                if (layEvent === 'btn_view') {
                    if(prefix=='dict'){
                        myformEdit('查看', url + '/toForm', {id: data.dictCode,code:data.dictType}, {id:'dictCode',code:'dictType'}, 800, 600);
                    }else
                    //查看
                    myformEdit('查看', url + '/toForm', {id: data.id}, tip, 800, 600);
                } else if (layEvent === 'btn_view_detail') {
                    //查看明细
                    myformEdit(data.name, url + '/info/list', {id: data.id,code:data.name}, {id:'id',code:'name'}, 850, 680);
                    //initFunction.tabFunc(element, "首页", "/welcome");
                   // sessionStorage.setItem("warehouse", data.id);
                } else if (layEvent === 'btn_edit') {
                    if(prefix=='dict'){
                        myformEdit('编辑', url + '/edit', {id: data.dictCode,code:data.dictType}, {id:'dictCode',code:'dictType'}, 800, 600);
                    }else
                    //编辑
                    myformEdit('编辑', url + '/edit', {id: data.id}, tip, 800, 600)
                } else if (layEvent === 'btn_del') {
                    //删除
                    layer.confirm('您确定要删除该' + title + '吗？', {icon: 3, title: title + '删除'}, function (index) {
                        layui.$.get(url + "/del?id=" + data.id, function (result) {
                            layer.msg(result.msg, {
                                icon: 1,
                                time: 1200
                            }, function () {
                                initFunction.refreshList(prefix, url);
                            });
                        })
                    });
                }
            });
        }
    }
});
