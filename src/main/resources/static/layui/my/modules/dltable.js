/**
 @Name：dltable普通表格
 @Author：
 @version: 0.1
 */
// layui.config({
//     base: '../../static/layui/my/modules/' //存放拓展模块的根目录
// }).extend({
//     treeGrid:'treeGrid'
// }).define(['laytpl', 'laypage','treeGrid', 'layer', 'form'], function(exports){
//     "use strict";
//     var $ = layui.jquery;
//     var treeGrid0 = layui.treeGrid;
//     var MOD_NAME='dltable';
//     var dltable=$.extend({},treeGrid0);
//     dltable._render=dltable.render;
//     dltable.render=function(param){//重写渲染方法
//         param.isTree=false;//普通表格
//         param.isPage=true;//默认分页
//         dltable._render(param);
//     };
//     exports(MOD_NAME, dltable);
// });