layui.use(['jquery','treeGrid','layer'], function(){
    var $=layui.jquery,
    treeGrid = layui.treeGrid,//很重要
    layer=layui.layer;
    ptable=treeGrid.render({
        id:'areaTreeList'
        ,elem: '#areaTreeList'
        ,idField:'id'
        ,url:'/area/getTreeList'
        ,cellMinWidth: 100
        ,treeId:'id'//树形id字段名称
        ,treeUpId:'parentId'//树形父id字段名称
        ,treeShowName:'name'//以树形式显示的字段
        ,cols: [[
            {type:'numbers'}
            ,{type:'checkbox',sort:true}
            ,{field:'name',/*edit:'text',*/width:'50%', title: '名称'}
            ,{field:'code', width: '10%', title: '行政编码'}
            ,{field:'levelName', width: '10%', title: '层级'}
            , {field: 'sort',  width: '10%',title: '排序'}
            ,{width:160,title: '操作', align:'center'/*toolbar: '#barDemo'*/
                ,templet: function(d){
                   // '&ensp;&ensp;<a  lay-event="start"  title="启用"><i class="layui-icon">&#xe601;</i></a>' +
                    var html = '&ensp;&ensp;<a  lay-event="show"  title="查看"><i class="layui-icon">&#xe615;</i></a>' +
                        '&ensp;&ensp;<a  lay-event="edit"  title="编辑"><i class="layui-icon">&#xe642;</i></a>' +
                        '&ensp;&ensp;<a  lay-event="delete"  title="删除"><i class="layui-icon">&#xe640;</i></a>' ;
                    return html;
                    // return '&ensp;&ensp;<a onclick="area_stop(this,\'' + row.id + '\')" href="javascript:;" title="启用"><i class="layui-icon">&#xe601;</i></a>' +
                    //     // '&ensp;&ensp;<a title="添加子类" onclick="myformShow(\'添加\',\'./area/add\',600,500)" href="javascript:;"><i class="layui-icon">&#xe654;</i></a>' +
                    //     '&ensp;&ensp;<a title="编辑" onclick="myformEdit(\'编辑\',\'./area/edit\',\'' + row.id + '\',\'id\',600,500)" href="javascript:;"><i class="layui-icon">&#xe642;</i></a>' +
                    //     '&ensp;&ensp;<a title="删除" onclick="del(' + row.id + ')" href="javascript:;">\<i class="layui-icon">&#xe640;</i></a>';
                }
            }
        ]]
        ,page:false
        ,parseData:function (res) {//数据加载后回调
            return res;
        }
        // ,onClickRow:function (index, o) {
        //     console.log(index,o,"单击！");
        // }
        // ,onDblClickRow:function (index, o) {
        //     console.log(index,o,"双击");
        // }
    });

    treeGrid.on('tool(area_tree_list)',function (obj) {
        if(obj.event === 'delete'){//删除行
            del(obj);
        }else if(obj.event==="show"){//查看
            myformEdit('查看','./area/toForm',{'id':obj.data.id},{'id':'id'},600,500);
        }else if(obj.event==="edit"){//编辑
            myformEdit('编辑','./area/edit', {'id':obj.data.id},{'id':'id'},600,500);
        }else if(obj.event==="start"){//启停
           //strat(obj);
        }
    });

    // $('#collapse').on('click', function () {
    //     layui.collapse(ptable);
    // });
    //
    // $('#expand').on('click', function () {
    //     layui.expand(ptable);
    // });

    // $('#expand').on('click', function () {
    //     var treedata=treeGrid.getDataTreeList(tableId);
    //     treeGrid.treeOpenAll(tableId,!treedata[0][treeGrid.config.cols.isOpen]);
    // });

});

function openAll() {
    var treedata=layui.treeGrid.getDataTreeList('areaTreeList');
    layui.treeGrid.treeOpenAll('areaTreeList',!treedata[0][layui.treeGrid.config.cols.isOpen]);
}

function reload() {
    layui.treeGrid.reload('areaTreeList',{
        page:{
            curr:1
        }
    });
}


function del(obj) {
    layer.confirm("你确定删除数据吗？如果存在下级节点则一并删除，此操作不能撤销！", {icon: 3, title:'提示'},
        function(index){//确定回调
         //console.log(JSON.stringify(obj));
            layui.$.ajax({
                url: '/area/del?id='+obj.data.id,
                type: 'GET',
                dataType: "json",
                success: function (result) {
                    if (result.code == 200 && result.data) {
                        obj.del();
                    } else {
                        layer.msg(result.msg);
                    }
                }
            });
            layer.close(index);
        },function (index) {//取消回调
            layer.close(index);
        }
    );
}



/*停用*/
// function area_stop(obj, id) {
//     var confirmTip;
//     if ($(obj).attr('title') == '启用') {
//         confirmTip = '确认要停用吗？';
//     } else {
//         confirmTip = '确认要启用吗？';
//     }
//     layer.confirm(confirmTip, function (index) {
//         if ($(obj).attr('title') == '启用') {
//             //发异步把用户状态进行更改
//             $(obj).attr('title', '停用')
//             $(obj).find('i').html('&#xe62f;');
//             $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
//             layer.msg('已停用!', {
//                 icon: 5,
//                 time: 1000
//             });
//         } else {
//             $(obj).attr('title', '启用')
//             $(obj).find('i').html('&#xe601;');
//
//             $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
//             layer.msg('已启用!', {
//                 icon: 6,
//                 time: 1000
//             });
//         }
//     });
// }
